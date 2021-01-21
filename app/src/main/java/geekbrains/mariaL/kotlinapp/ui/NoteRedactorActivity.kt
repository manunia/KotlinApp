package geekbrains.mariaL.kotlinapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import geekbrains.mariaL.kotlinapp.databinding.NoteRedactorBinding
import geekbrains.mariaL.kotlinapp.model.Note

class NoteRedactorActivity : AppCompatActivity() {

    lateinit var ui: NoteRedactorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = NoteRedactorBinding.inflate(layoutInflater)
        setContentView(ui.root)

        ui.saveButton.setOnClickListener {
            var summury: String = ui.note.text.substring(0, 100)


        }
    }
}