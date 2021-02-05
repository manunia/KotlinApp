package geekbrains.mariaL.kotlinapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import geekbrains.mariaL.kotlinapp.R
import geekbrains.mariaL.kotlinapp.databinding.NoteRedactorBinding
import geekbrains.mariaL.kotlinapp.model.Color
import geekbrains.mariaL.kotlinapp.model.Note
import geekbrains.mariaL.kotlinapp.viewmodel.NoteViewModel
import java.util.*

private const val SAVE_DELAY = 2000L

class NoteRedactorActivity : BaseActivity<NoteViewState.Data, NoteViewState>() {

    companion object {
        const val EXTRA_NOTE = "NoteRedactorActivity.extra.NOTE"

        fun getStartIntent(context: Context, noteId: String?): Intent {
            val intent = Intent(context, NoteRedactorActivity::class.java)
            intent.putExtra(EXTRA_NOTE, noteId)
            return intent
        }
    }

    private var note: Note? = null
    private var color: Color = Color.RED
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
        setSupportActionBar(ui.toolbar)
        val noteId = intent.getStringExtra(EXTRA_NOTE)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        noteId?.let {
            viewModel.loadNote(it)
        } ?: run {
            supportActionBar?.title = getString(R.string.new_note_title)
        }
        ui.colorPicker.onColorClickListener = {
            color = it
            setToolBarColor(it)
            triggerSaveNote()
        }
        setEditListener()
    }

    private fun initView() {
        note?.run {
            removeEditListener()
            if (title != ui.title.text.toString()) {
                ui.title.setText(title)
            }
            if (note != ui.note.text.toString()) {
                ui.note.setText(note)
            }
            setEditListener()
            supportActionBar?.title = modifyDate.format()
            setToolBarColor(color)
        }
    }

    private fun setToolBarColor(color : Color) {
        ui.toolbar.setBackgroundColor(color.getColorInt(this@NoteRedactorActivity))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean =
        menuInflater.inflate(R.menu.menu_note, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> super.onBackPressed().let { true }
        R.id.palette -> togglePallete().let { true }
        R.id.delete -> deleteNote().let { true }
        else -> super.onOptionsItemSelected(item)
    }

    private fun togglePallete() {
        if(ui.colorPicker.isOpen) {
            ui.colorPicker.close()
        } else {
            ui.colorPicker.open()
        }
    }

    private fun deleteNote() {
        AlertDialog.Builder(this)
            .setMessage(R.string.delete_note_message)
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .setPositiveButton("ОК") { _, _ -> viewModel.deleteNote() }
            .show()
    }

    private fun triggerSaveNote() {
        if (ui.title.text == null || ui.title.text!!.length < 3) return

        Handler(Looper.getMainLooper()).postDelayed({
            note = note?.copy(
                title = ui.title.text.toString(),
                note = ui.note.text.toString(),
                color = color,
                modifyDate = Date()
            ) ?: createNewNote()

            if (note != null) viewModel.saveChanges(note!!)
        }, SAVE_DELAY)
    }

    override fun renderData(data: NoteViewState.Data) {
        if (data.isDeleted) finish()

        this.note = data.note
        data?.note?.let { color = it.color }

        initView()
    }

    private fun createNewNote(): Note = Note(
        UUID.randomUUID().toString(),
        ui.title.text.toString(),
        ui.note.text.toString()
    )

    private fun setEditListener() {
        ui.title.addTextChangedListener(textChangeListener)
        ui.note.addTextChangedListener(textChangeListener)
    }

    private fun removeEditListener() {
        ui.title.removeTextChangedListener(textChangeListener)
        ui.note.removeTextChangedListener(textChangeListener)
    }

    override fun onBackPressed() {
        if (ui.colorPicker.isOpen) {
            ui.colorPicker.close()
            return
        }
        super.onBackPressed()
    }
}