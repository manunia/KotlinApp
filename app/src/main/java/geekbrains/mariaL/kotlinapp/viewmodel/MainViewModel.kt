package geekbrains.mariaL.kotlinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import geekbrains.mariaL.kotlinapp.model.Repository
import geekbrains.mariaL.kotlinapp.ui.MainViewState

class MainViewModel: ViewModel() {
    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    init {
        viewStateLiveData.value = MainViewState(Repository.notes)
    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData

    fun addNote(title: String, severity: String, date: String, summary: String, note: String, color: Int) {
        Repository.addNote(title, severity, date, summary, note, color)
    }
}