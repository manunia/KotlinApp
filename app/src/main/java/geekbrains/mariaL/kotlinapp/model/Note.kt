package geekbrains.mariaL.kotlinapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Note (
        val id: String,
        val title: String,
        val severity: String,
        val modifyDate: Date = Date(),
        val summary: String,
        val note: String,
        val color: Int) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false
        if (title != other.title) return false
        if (severity != other.severity) return false
        if (modifyDate != other.modifyDate) return false
        if (summary != other.summary) return false
        if (note != other.note) return false
        if (color != other.color) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + severity.hashCode()
        result = 31 * result + modifyDate.hashCode()
        result = 31 * result + summary.hashCode()
        result = 31 * result + note.hashCode()
        result = 31 * result + color
        return result
    }
}