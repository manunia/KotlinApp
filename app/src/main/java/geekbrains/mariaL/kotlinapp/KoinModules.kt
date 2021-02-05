package geekbrains.mariaL.kotlinapp

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import geekbrains.mariaL.kotlinapp.providers.FireStoreProvider
import geekbrains.mariaL.kotlinapp.providers.RemoteDataProvider
import geekbrains.mariaL.kotlinapp.repo.Repository
import geekbrains.mariaL.kotlinapp.viewmodel.MainViewModel
import geekbrains.mariaL.kotlinapp.viewmodel.NoteViewModel
import geekbrains.mariaL.kotlinapp.viewmodel.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel

import org.koin.dsl.bind
import org.koin.dsl.module


val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FireStoreProvider(get(), get()) } bind RemoteDataProvider::class
    single { Repository(get()) }

    viewModel { SplashViewModel(get())}
    viewModel { MainViewModel(get())}
    viewModel { NoteViewModel(get())}
}

