package geekbrains.mariaL.kotlinapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import geekbrains.mariaL.kotlinapp.databinding.NoteRedactorBinding

class NoteRedactorActivity: AppCompatActivity() {

    lateinit var ui: NoteRedactorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = NoteRedactorBinding.inflate(layoutInflater)
        setContentView(ui.root)
    }
}