package geekbrains.mariaL.kotlinapp

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : Button = findViewById(R.id.button)

        button.setOnClickListener{
            (it as Button).text = "Pressed"
        }

    }
}