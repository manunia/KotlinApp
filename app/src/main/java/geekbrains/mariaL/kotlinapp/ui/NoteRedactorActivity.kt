package geekbrains.mariaL.kotlinapp.ui

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import geekbrains.mariaL.kotlinapp.R
import geekbrains.mariaL.kotlinapp.databinding.NoteRedactorBinding
import geekbrains.mariaL.kotlinapp.model.Note
import geekbrains.mariaL.kotlinapp.model.Repository
import geekbrains.mariaL.kotlinapp.viewmodel.MainViewModel
import java.time.LocalDate
import java.util.*

class NoteRedactorActivity : AppCompatActivity() {

    lateinit var ui: NoteRedactorBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = NoteRedactorBinding.inflate(layoutInflater)
        setContentView(ui.root)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        var currentDate = sdf.format(Date())
        ui.date.text.insert(0, currentDate)

        ui.saveButton.setOnClickListener {
            val noteText = ui.note.text.toString()
            val summary: String = noteText.substring(0, noteText.length%10 + 1)

            Repository.addNote(
                    ui.title.text.toString(),
                    ui.severity.text.toString(),
                    currentDate,
                    summary,
                    ui.note.text.toString(),
                    0xfff06292.toInt()
            )
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("NOTE", Note(
                    ui.title.text.toString(),
                    ui.severity.text.toString(),
                    currentDate,
                    summary,
                    ui.note.text.toString(),
                    0xfff06292.toInt()
            ))
            setContentView(R.layout.activity_main)
        }
    }
}