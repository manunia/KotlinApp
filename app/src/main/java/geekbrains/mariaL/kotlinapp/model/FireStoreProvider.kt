package geekbrains.mariaL.kotlinapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.*

private const val NOTES_COLLECTION = "notes"

class FireStoreProvider : RemoteDataProvider {

    companion object {
        private val TAG = "${FireStoreProvider::class.java.simpleName} :"
    }

    private val db = FirebaseFirestore.getInstance()
    private val notesReferences = db.collection(NOTES_COLLECTION)

    override fun subscribeToAllNotes(): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()

        notesReferences.addSnapshotListener { value, error ->
            if (error != null) {
                result.value = NoteResult.Error(error)
            } else if (value != null) {
                val notes = mutableListOf<Note>()

                for (doc: QueryDocumentSnapshot in value) {
                    notes.add(doc.toObject(Note::class.java))
                }
                result.value = NoteResult.Success(notes)
            }
        }
        return result
    }

    override fun getNoteById(id: String): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()
        notesReferences.document(id)
                .get()
                .addOnSuccessListener { snapshot ->
                    result.value =
                            NoteResult.Success(snapshot.toObject(Note::class.java))
                }
                .addOnFailureListener { ex ->
                    result.value = NoteResult.Error(ex)
                }
    }

    override fun saveNote(note: Note): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()

        notesReferences.document(note.id)
                .set(note)
                .addOnSuccessListener {
                    Log.d(TAG, "Note $note is saved")
                    result.value = NoteResult.Success(note)
                }.addOnFailureListener { ex ->
                    Log.d(TAG, "Error saving note $note, message: ${ex.message}")
                    result.value = NoteResult.Error(ex)

                }
        return result
    }
}