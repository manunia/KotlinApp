package geekbrains.mariaL.kotlinapp.repo

import geekbrains.mariaL.kotlinapp.model.Note
import geekbrains.mariaL.kotlinapp.providers.FireStoreProvider
import geekbrains.mariaL.kotlinapp.providers.RemoteDataProvider

object Repository {

    private val remoteProvider: RemoteDataProvider = FireStoreProvider()

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    fun getCurrentUser() = remoteProvider.getCurrentUser()
    fun deleteNote(noteId: String) = remoteProvider.deleteNote(noteId)

}