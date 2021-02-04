package geekbrains.mariaL.kotlinapp.providers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import geekbrains.mariaL.kotlinapp.exceptions.NoAuthException
import geekbrains.mariaL.kotlinapp.model.Note
import geekbrains.mariaL.kotlinapp.model.NoteResult
import geekbrains.mariaL.kotlinapp.model.User
import geekbrains.mariaL.kotlinapp.providers.RemoteDataProvider

private const val NOTES_COLLECTION = "notes"
private const val USERS_COLLECTION = "users"
private val TAG = "${FireStoreProvider::class.java.simpleName} :"

class FireStoreProvider : RemoteDataProvider {

    private val db = FirebaseFirestore.getInstance()
    private val notesReferences = db.collection(NOTES_COLLECTION)
    private val currentUser
        get() = FirebaseAuth.getInstance().currentUser

    override fun subscribeToAllNotes(): LiveData<NoteResult> =
        MutableLiveData<NoteResult>().apply {

            try {
                notesReferences.addSnapshotListener { snapshot, error ->
                    value = error?.let { NoteResult.Error(it) }
                        ?: snapshot?.let { query ->
                            val notes = query.documents.map { document ->
                                document.toObject(Note::class.java)
                            }
                            NoteResult.Success(notes)
                        }
                }
            } catch (e: Throwable) {
                value = NoteResult.Error(e)
            }
        }

    override fun getNoteById(id: String): LiveData<NoteResult> =
        MutableLiveData<NoteResult>().apply {
            notesReferences.document(id)
                .get()
                .addOnSuccessListener { snapshot ->
                    value = NoteResult.Success(snapshot.toObject(Note::class.java))
                }
                .addOnFailureListener { ex ->
                    value = NoteResult.Error(ex)
                }
        }


    override fun saveNote(note: Note): LiveData<NoteResult> =
        MutableLiveData<NoteResult>().apply {
            notesReferences.document(note.id)
                .set(note)
                .addOnSuccessListener {
                    Log.d(TAG, "Note $note is saved")
                    value = NoteResult.Success(note)
                }.addOnFailureListener { ex ->
                    Log.d(TAG, "Error saving note $note, message: ${ex.message}")
                    value = NoteResult.Error(ex)
                }
        }

    private fun getUserNotesCollection() = currentUser?.let { user ->
        db.collection(USERS_COLLECTION)
            .document(user.uid)
            .collection(NOTES_COLLECTION)
    } ?: throw NoAuthException()

    override fun getCurrentUser(): LiveData<User?> =
        MutableLiveData<User?>().apply {
            value = currentUser?.let {
                User(
                    it.displayName ?: "",
                    it.email ?: ""
                )
            }
        }

    override fun deleteNote(noteId: String): LiveData<NoteResult> =
        MutableLiveData<NoteResult>().apply {
            try {
                getUserNotesCollection()
                    .document(noteId)
                    .delete()
                    .addOnSuccessListener {
                        value = NoteResult.Success(null)
                    }
                    .addOnFailureListener {
                        value = NoteResult.Error(it)
                    }
            } catch (e: Throwable) {
                value = NoteResult.Error(e)
            }
        }

}