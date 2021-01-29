package geekbrains.mariaL.kotlinapp.ui

import geekbrains.mariaL.kotlinapp.model.Note

class NoteViewState(note : Note? = null, error: Throwable? = null) :
        BaseViewState<Note?>(note, error) {
}