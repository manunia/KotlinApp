package geekbrains.mariaL.kotlinapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import geekbrains.mariaL.kotlinapp.databinding.ActivityMainBinding
import geekbrains.mariaL.kotlinapp.model.Note
import geekbrains.mariaL.kotlinapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var ui: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        setSupportActionBar(ui.toolbar)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        adapter = MainAdapter(object: OnItemClickListener {
            override fun onItemClick(note: Note) {
                openNoteRedactor(note)
            }
        })
        ui.mainRecycler.adapter = adapter

        viewModel.viewState().observe(this, Observer<MainViewState> { state ->
            state?.let { adapter.notes = state.notes }
        })

        ui.fab.setOnClickListener{
            openNoteRedactor()
        }

    }

    private fun openNoteRedactor(note: Note? = null) {
        startActivity(NoteRedactorActivity.getStartIntent(this, note))
    }

}