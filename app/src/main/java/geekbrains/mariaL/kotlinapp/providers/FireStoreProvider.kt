package geekbrains.mariaL.kotlinapp.providers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import geekbrains.mariaL.kotlinapp.exceptions.NoAuthException
import geekbrains.mariaL.kotlinapp.model.Note
import geekbrains.mariaL.kotlinapp.model.NoteResult
import geekbrains.mariaL.kotlinapp.model.User
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private const val NOTES_COLLECTION = "notes"
private const val USERS_COLLECTION = "users"
private val TAG = "${FireStoreProvider::class.java.simpleName} :"

class FireStoreProvider(
    private val fireBaseAuth: FirebaseAuth,
    private val db: FirebaseFirestore
) : RemoteDataProvider {

    private val notesReferences = db.collection(NOTES_COLLECTION)
    private val currentUser
        get() = fireBaseAuth.currentUser

    override suspend fun subscribeToAllNotes(): ReceiveChannel<NoteResult> =
        Channel<NoteResult>(Channel.CONFLATED).apply {
            var registration: ListenerRegistration? = null
            try {
                registration = getUserNotesCollection()
                    .addSnapshotListener { snapshot, e ->
                        val value = e?.let {
                            NoteResult.Error(it)
                        } ?: snapshot?.let {
                            val notes = it.documents.map { document ->
                                document.toObject(Note::class.java)
                            }
                            NoteResult.Success(it)
                        }
                        value?.let { offer(it) }
                    }
            } catch (e: Throwable) {
                offer(NoteResult.Error(e))
            }
            invokeOnClose { registration?.remove() }

        }

    override suspend fun getNoteById(id: String): Note =
        suspendCoroutine { continuation ->
            notesReferences.document(id)
                .get()
                .addOnSuccessListener { snapshot ->
                    continuation.resume(snapshot.toObject(Note::class.java)!!)
                }
                .addOnFailureListener { ex ->
                    continuation.resumeWithException(ex)
                }
        }


    override suspend fun saveNote(note: Note): Note =
        suspendCoroutine { continuation ->
            notesReferences.document(note.id)
                .set(note)
                .addOnSuccessListener {
                    continuation.resume(note)
                }.addOnFailureListener { ex ->
                    continuation.resumeWithException(ex)
                }
        }

    private fun getUserNotesCollection() = currentUser?.let { user ->
        db.collection(USERS_COLLECTION)
            .document(user.uid)
            .collection(NOTES_COLLECTION)
    } ?: throw NoAuthException()

    override suspend fun getCurrentUser(): User =
        suspendCoroutine { continuation ->
            currentUser?.let {
                continuation.resume(
                    User(
                        it.displayName ?: "",
                        it.email ?: ""
                    )
                )
            } ?: continuation.resumeWithException(NoAuthException())
        }

    override suspend fun deleteNote(noteId: String): Note? =
        suspendCoroutine { continuation ->
            try {
                getUserNotesCollection()
                    .document(noteId)
                    .delete()
                    .addOnSuccessListener {
                        continuation.resume(null)
                    }
                    .addOnFailureListener {
                        continuation.resumeWithException(it)
                    }
            } catch (e: Throwable) {
                continuation.resumeWithException(e)
            }
        }

}