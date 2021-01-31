package geekbrains.mariaL.kotlinapp.ui

import android.content.Context
import androidx.appcompat.app.AlertDialog

class MyAlertDialogBuilder(val context: Context, val title: String, val message: String = "") {

    fun build() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("ОК") { dialog, id ->
                    dialog.cancel()
                }
        builder.create()
    }

}