package geekbrains.mariaL.kotlinapp.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import geekbrains.mariaL.kotlinapp.R
import geekbrains.mariaL.kotlinapp.databinding.ActivityMainBinding
import geekbrains.mariaL.kotlinapp.model.Note
import geekbrains.mariaL.kotlinapp.viewmodel.MainViewModel

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    override val viewModel: MainViewModel
            by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override val ui: ActivityMainBinding
            by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override val layoutRes: Int = R.layout.activity_main
    val adapter: MainAdapter by lazy {
        MainAdapter(object : OnItemClickListener {
            override fun onItemClick(note: Note) {
                openNoteRedactor(note)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui.root)

        setSupportActionBar(ui.toolbar)

        ui.mainRecycler.adapter = adapter

        ui.fab.setOnClickListener {
            openNoteRedactor(Note())
        }

    }

    private fun openNoteRedactor(note: Note?) {
        startActivity(NoteRedactorActivity.getStartIntent(this, note?.id))
    }

    override fun renderData(data: List<Note>?) {
        if (data == null) return
        adapter.notes = data
    }

}