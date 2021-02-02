package geekbrains.mariaL.kotlinapp.viewmodel

import geekbrains.mariaL.kotlinapp.exceptions.NoAuthException
import geekbrains.mariaL.kotlinapp.repo.Repository
import geekbrains.mariaL.kotlinapp.ui.SplashViewState

class SplashViewModel(private val repositoty: Repository = Repository) :
    BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        repositoty.getCurrentUser().observeForever { user ->
            viewStateLiveData.value = user?.let {
                SplashViewState(isAuth = true)
            } ?: SplashViewState(error = NoAuthException())
        }
    }
}