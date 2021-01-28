package geekbrains.mariaL.kotlinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import geekbrains.mariaL.kotlinapp.ui.BaseViewState

open class BaseViewModel<T, VS : BaseViewState<T>>() : ViewModel() {
    open val viewStateLiveData = MutableLiveData<VS>()

    open fun getViewState(): LiveData<VS> = viewStateLiveData
}