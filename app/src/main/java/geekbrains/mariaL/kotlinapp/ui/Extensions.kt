package geekbrains.mariaL.kotlinapp.ui

import android.content.Context
import androidx.core.content.ContextCompat
import geekbrains.mariaL.kotlinapp.R
import geekbrains.mariaL.kotlinapp.model.Color
import java.text.SimpleDateFormat
import java.util.*

const val DATE_TIME_FORMAT = "dd.MMM.yy HH:mm"

fun Date.format(): String = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(this)

fun Color.getColorInt(context: Context): Int =
    ContextCompat.getColor(
        context, getColorRes()
    )

fun Color.getColorRes(): Int = when (this) {
    Color.WHITE -> R.color.color_white
    Color.VIOLET -> R.color.color_violet
    Color.YELLOW -> R.color.color_yello
    Color.RED -> R.color.color_red
    Color.PINK -> R.color.color_pink
    Color.GREEN -> R.color.color_green
    Color.BLUE -> R.color.color_blue
}

fun Context.dip(value: Int) : Int = (value * resources.displayMetrics.density).toInt()

