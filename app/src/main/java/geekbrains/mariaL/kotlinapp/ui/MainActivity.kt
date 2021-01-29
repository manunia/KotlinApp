package geekbrains.mariaL.kotlinapp.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import geekbrains.mariaL.kotlinapp.R
import geekbrains.mariaL.kotlinapp.databinding.ActivityMainBinding
import geekbrains.mariaL.kotlinapp.model.Note
import geekbrains.mariaL.kotlinapp.viewmodel.MainViewModel

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    override val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    override val layoutRes: Int = R.layout.activity_main
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        setSupportActionBar(ui.toolbar)

        adapter = MainAdapter(object : OnItemClickListener {
            override fun onItemClick(note: Note) {
                openNoteRedactor(note)
            }
        })
        ui.mainRecycler.adapter = adapter

        ui.fab.setOnClickListener {
            openNoteRedactor()
        }

    }

    private fun openNoteRedactor(note: Note? = null) {
        startActivity(NoteRedactorActivity.getStartIntent(this, note?.id))
    }

    override fun renderData(data: List<Note>?) {
        if (data == null) return
        adapter.notes = data
    }

}