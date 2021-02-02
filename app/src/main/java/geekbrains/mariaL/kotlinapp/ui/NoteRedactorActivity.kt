package geekbrains.mariaL.kotlinapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import geekbrains.mariaL.kotlinapp.R
import geekbrains.mariaL.kotlinapp.databinding.NoteRedactorBinding
import geekbrains.mariaL.kotlinapp.model.Note
import geekbrains.mariaL.kotlinapp.viewmodel.NoteViewModel
import java.util.*

private const val SAVE_DELAY = 2000L

class NoteRedactorActivity : BaseActivity<Note?, NoteViewState>() {

    companion object {
        const val EXTRA_NOTE = "NoteRedactorActivity.extra.NOTE"

        fun getStartIntent(context: Context, noteId: String?): Intent {
            val intent = Intent(context, NoteRedactorActivity::class.java)
            intent.putExtra(EXTRA_NOTE, noteId)
            return intent
        }
    }

    private var note: Note? = null
    override val viewModel: NoteViewModel
            by lazy { ViewModelProvider(this).get(NoteViewModel::class.java) }
    override val ui: NoteRedactorBinding
            by lazy { NoteRedactorBinding.inflate(layoutInflater) }
    override val layoutRes: Int = R.layout.note_redactor

    private val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            triggerSaveNote()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val noteId = intent.getStringExtra(EXTRA_NOTE)

        if (noteId == null) supportActionBar?.title = getString(R.string.new_note_title)
        noteId?.let {
            viewModel.loadNote(it)
        }

        initView()
    }

    private fun initView() {
        note?.run {
            ui.title.setText(title)
            ui.note.setText(note)

            ui.date.setText(modifyDate.format())
            ui.toolbar.setBackgroundColor(color.getColorInt(this@NoteRedactorActivity))
            supportActionBar?.title = modifyDate.format()
        }

        ui.title.addTextChangedListener(textChangeListener)
        ui.note.addTextChangedListener(textChangeListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun triggerSaveNote() {
        if (ui.title.text == null || ui.title.text!!.length < 3) return

        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                note = note?.copy(
                    title = ui.title.text.toString(),
                    note = ui.note.text.toString(),
                    modifyDate = Date()
                ) ?: createNewNote()

                if (note != null) viewModel.saveChanges(note!!)
            }

        }, SAVE_DELAY)
    }

    override fun renderData(data: Note?) {
        note = data
        initView()
    }

    private fun createNewNote(): Note = Note(
        UUID.randomUUID().toString(),
        ui.title.text.toString()

    )

}