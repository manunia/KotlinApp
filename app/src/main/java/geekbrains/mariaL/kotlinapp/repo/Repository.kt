package geekbrains.mariaL.kotlinapp.repo

import geekbrains.mariaL.kotlinapp.model.Note
import geekbrains.mariaL.kotlinapp.providers.FireStoreProvider
import geekbrains.mariaL.kotlinapp.providers.RemoteDataProvider

class Repository(private val remoteProvider: RemoteDataProvider) {

    suspend fun getNotes() = remoteProvider.subscribeToAllNotes()
    suspend fun saveNote(note: Note) = remoteProvider.saveNote(note)
    suspend fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    suspend fun getCurrentUser() = remoteProvider.getCurrentUser()
    suspend fun deleteNote(noteId: String) = remoteProvider.deleteNote(noteId)

}