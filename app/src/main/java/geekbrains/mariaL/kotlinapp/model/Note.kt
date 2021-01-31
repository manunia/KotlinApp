package geekbrains.mariaL.kotlinapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Note (
        val id: String = "",
        val title: String = "",
        val severity: Severity = Severity.MIDDLE,
        val modifyDate: Date = Date(),
        val summary: String = "",
        val note: String = "",
        val color: Color = Color.WHITE
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}


enum class Color {
    WHITE,
    YELLOW,
    GREEN,
    BLUE,
    RED,
    VIOLET,
    PINK
}

 enum class Severity {
     WERY_HIGHT,
     HIGHT,
     MIDDLE,
     LOW,
     NOT_MATTER
 }