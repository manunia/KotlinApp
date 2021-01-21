package geekbrains.mariaL.kotlinapp.model

object Repository {
    var notes: MutableList<Note> = mutableListOf(
                Note("Моя первая заметка",
                        "1",
                        "01.01.2020",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xfff06292.toInt()),
                Note("Моя вторая заметка",
                        "1",
                        "01.01.2020",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xfff06292.toInt()),
                Note("Моя третья заметка",
                        "1",
                        "01.01.2020",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xfff06292.toInt()),
                Note("Моя четвертая заметка",
                        "1",
                        "01.01.2020",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xfff06292.toInt()),
                Note("Моя пятая заметка",
                        "1",
                        "01.01.2020",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xfff06292.toInt())
                )

    fun addNote(title: String, severity: String, date: String, summary: String, note: String, color: Int) {
        notes.add(Note(title,severity, date, summary, note, color))
    }
}