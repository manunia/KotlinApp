package geekbrains.mariaL.kotlinapp.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import geekbrains.mariaL.kotlinapp.viewmodel.BaseViewModel

class BaseActivity<T, VS : BaseViewState<T>> : AppCompatActivity() {

    abstract val viewModel : BaseViewModel<T, VS>
    abstract val layourRes: Int
    abstract val ui: ViewBinding
}