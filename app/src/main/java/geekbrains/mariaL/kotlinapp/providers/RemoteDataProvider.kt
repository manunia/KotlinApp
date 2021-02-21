package geekbrains.mariaL.kotlinapp.providers

import geekbrains.mariaL.kotlinapp.model.Note
import geekbrains.mariaL.kotlinapp.model.NoteResult
import geekbrains.mariaL.kotlinapp.model.User
import kotlinx.coroutines.channels.ReceiveChannel

interface RemoteDataProvider {

    suspend fun subscribeToAllNotes(): ReceiveChannel<NoteResult>
    suspend fun getNoteById(id: String): Note
    suspend fun saveNote(note: Note): Note
    suspend fun getCurrentUser(): User
    suspend fun deleteNote(noteId: String): Note?
}