package geekbrains.mariaL.kotlinapp.viewmodel

import androidx.lifecycle.ViewModel
import geekbrains.mariaL.kotlinapp.databinding.NoteRedactorBinding
import geekbrains.mariaL.kotlinapp.model.Note
import geekbrains.mariaL.kotlinapp.model.Repository
import geekbrains.mariaL.kotlinapp.model.Severity
import java.util.*


class NoteViewModel(private val repository: Repository = Repository, private val ui: NoteRedactorBinding) : ViewModel() {

    private var pendingNote: Note? = null

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        if (pendingNote != null) {
            repository.saveNote(pendingNote!!)
        }
    }

    fun createNewNote(): Note = Note(
            UUID.randomUUID().toString(),
            ui.title.text.toString(),
            Severity.NOT_MATTER,
            Date(),
            ui.note.text.toString(),
            ui.note.text.toString()
    )
}
