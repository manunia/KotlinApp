package geekbrains.mariaL.kotlinapp.ui

import geekbrains.mariaL.kotlinapp.model.Note

class NoteViewState(data: Data = Data(), isDeleted: Boolean = false, error: Throwable? = null) :
    BaseViewState<NoteViewState.Data>(data, error) {

    data class Data(val isDeleted: Boolean = false, val note: Note? = null)
}