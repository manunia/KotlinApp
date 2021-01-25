package geekbrains.mariaL.kotlinapp.ui

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import geekbrains.mariaL.kotlinapp.R
import geekbrains.mariaL.kotlinapp.databinding.NoteRedactorBinding
import geekbrains.mariaL.kotlinapp.model.Color
import geekbrains.mariaL.kotlinapp.model.Note
import geekbrains.mariaL.kotlinapp.model.Severity
import geekbrains.mariaL.kotlinapp.viewmodel.NoteViewModel
import java.util.*

private const val SAVE_DELAY = 2000L

class NoteRedactorActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NOTE = "NoteRedactorActivity.extra.NOTE"

        fun getStartIntent(context: Context, note: Note?): Intent {
            val intent = Intent(context, NoteRedactorActivity::class.java)
            intent.putExtra(EXTRA_NOTE, note)
            return intent
        }
    }

    private var note: Note? = null
    lateinit var ui: NoteRedactorBinding
    private lateinit var viewModel: NoteViewModel
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
        ui = NoteRedactorBinding.inflate(layoutInflater)
        setContentView(ui.root)

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        note = intent.getParcelableExtra(EXTRA_NOTE)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = if (note != null) {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(note!!.modifyDate)
        } else {
            getString(R.string.new_note_title)
        }
        initView()
    }

    private fun initView() {
        ui.title.setText(note?.title ?: "")
        ui.note.setText(note?.note ?: "")
        val color = when (note?.color) {
            Color.WHITE -> R.color.color_white
            Color.VIOLET -> R.color.color_violet
            Color.YELLOW -> R.color.color_yello
            Color.RED -> R.color.color_red
            Color.PINK -> R.color.color_pink
            Color.GREEN -> R.color.color_green
            Color.BLUE -> R.color.color_blue
            else -> R.color.color_white
        }

        val severity = when (note?.severity) {
            Severity.WERY_HIGHT -> R.string.very_high
            Severity.HIGHT -> R.string.high
            Severity.MIDDLE -> R.string.middle
            Severity.LOW -> R.string.low
            Severity.NOT_MATTER -> R.string.not_matter
            else -> R.string.middle
        }

        ui.toolbar.setBackgroundColor(resources.getColor(color))
        ui.severity.setText(resources.getString(severity))
        ui.title.addTextChangedListener(textChangeListener)
        ui.severity.addTextChangedListener(textChangeListener)
        ui.note.addTextChangedListener(textChangeListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun createNewNote(): Note = Note(
            UUID.randomUUID().toString(),
            ui.title.text.toString(),
            Severity.NOT_MATTER,
            Date(),
            ui.note.text.toString(),
            ui.note.text.toString()
    )


    private fun triggerSaveNote() {
        if (ui.title.text == null || ui.title.text!!.length < 3) return

        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                note = note?.copy(title = ui.title.text.toString(),
                        note = ui.note.text.toString(),
                        modifyDate = Date())
                        ?: createNewNote()

                if (note != null) viewModel.saveChanges(note!!)
            }

        }, SAVE_DELAY)
    }

}