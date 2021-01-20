package geekbrains.mariaL.kotlinapp.model

object Repository {
    private val notes: List<Note> = listOf(
                Note("Моя первая заметка",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xfff06292.toInt()),
                Note("Моя вторая заметка",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xfff06292.toInt()),
                Note("Моя третья заметка",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xfff06292.toInt()),
                Note("Моя четвертая заметка",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xfff06292.toInt()),
                Note("Моя пятая заметка",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xfff06292.toInt())
                )
}