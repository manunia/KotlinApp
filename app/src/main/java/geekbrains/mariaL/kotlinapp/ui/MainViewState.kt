package geekbrains.mariaL.kotlinapp.ui

import geekbrains.mariaL.kotlinapp.model.Note

class MainViewState(val notes: List<Note>? = null, error: Throwable? = null) :
        BaseViewState<List<Note>?>(notes, error)