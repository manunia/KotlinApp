package geekbrains.mariaL.kotlinapp.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import geekbrains.mariaL.kotlinapp.R

class MyAlertDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(getString(R.string.title))
                    .setMessage(R.string.message)
                    .setPositiveButton("ОК") {
                        dialog, id ->  dialog.cancel()
                    }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}