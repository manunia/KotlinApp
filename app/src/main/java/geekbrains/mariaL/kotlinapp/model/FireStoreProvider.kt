package geekbrains.mariaL.kotlinapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.*
import com.google.firebase.firestore.FirebaseFirestore

private const val NOTES_COLLECTION = "notes"

class FireStoreProvider : RemoteDataProvider {

    companion object {
        private val TAG = "${FireStoreProvider::class.java.simpleName} :"
    }

    private val db = FirebaseFirestore.getInstance()
    private val notesReferences = db.collection(NOTES_COLLECTION)

    override fun subscribeToAllNotes(): LiveData<NoteResult> =
            MutableLiveData<NoteResult>().apply {
                notesReferences.addSnapshotListener { snapshot, error ->
                    value = error?.let { NoteResult.Error(it) }
                            ?: snapshot?.let { query ->
                                val notes = query.documents.map { document ->
                                    document.toObject(Note::class.java)
                                }
                                NoteResult.Success(notes)
                            }
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
}