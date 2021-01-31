package geekbrains.mariaL.kotlinapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import geekbrains.mariaL.kotlinapp.viewmodel.BaseViewModel

abstract class BaseActivity<T, VS : BaseViewState<T>> : AppCompatActivity() {

    abstract val viewModel: BaseViewModel<T, VS>
    abstract val layoutRes: Int
    abstract val ui: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui.root)

        viewModel.getViewState().observe(this) { t ->
            t?.apply {
                data?.let { renderData(it) }
                error?.let { renderError(it) }
            }
        }
    }

    protected fun renderError(error: Throwable) {
        error.message?.let { showError(it) }
    }

    abstract fun renderData(data: T)

    protected fun showError(error: String) {
        MyAlertDialogBuilder(this, "Error!", error).build()
    }
}