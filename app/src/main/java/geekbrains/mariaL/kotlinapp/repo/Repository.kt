package geekbrains.mariaL.kotlinapp.repo

import geekbrains.mariaL.kotlinapp.model.FireStoreProvider
import geekbrains.mariaL.kotlinapp.model.Note
import geekbrains.mariaL.kotlinapp.providers.RemoteDataProvider

object Repository {
    private val remoteProvider: RemoteDataProvider = FireStoreProvider()

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)


}