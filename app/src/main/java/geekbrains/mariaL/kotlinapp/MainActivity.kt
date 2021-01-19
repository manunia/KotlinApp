package geekbrains.mariaL.kotlinapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : Button = findViewById(R.id.button)

        button.setOnClickListener{
            val dialog = MyAlertDialog()
            val manager = supportFragmentManager
            dialog.show(manager, getString(R.string.message))
        }

    }
}