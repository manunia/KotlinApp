package geekbrains.mariaL.kotlinapp.ui

import android.content.Context
import androidx.core.content.ContextCompat
import geekbrains.mariaL.kotlinapp.R
import geekbrains.mariaL.kotlinapp.model.Color
import geekbrains.mariaL.kotlinapp.model.Severity
import java.text.SimpleDateFormat
import java.util.*

const val DATE_TIME_FORMAT = "dd.MMM.yy HH:mm"

fun Date.format(): String = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(this)

fun Color.getColorInt(context: Context): Int =
        ContextCompat.getColor(context, when (this) {
            Color.WHITE -> R.color.color_white
            Color.VIOLET -> R.color.color_violet
            Color.YELLOW -> R.color.color_yello
            Color.RED -> R.color.color_red
            Color.PINK -> R.color.color_pink
            Color.GREEN -> R.color.color_green
            Color.BLUE -> R.color.color_blue
        })

fun Severity.getSeverity(): Int =
        when (this) {
            Severity.WERY_HIGHT -> R.string.very_high
            Severity.HIGHT -> R.string.high
            Severity.MIDDLE -> R.string.middle
            Severity.LOW -> R.string.low
            Severity.NOT_MATTER -> R.string.not_matter
        }
