package geekbrains.mariaL.kotlinapp.model

import java.util.*

object Repository {
    val notes: MutableList<Note> = mutableListOf(
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    note = "Kotlin очень краткий, но при этом выразительный язык",
                    summary = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.WHITE),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    summary = "Kotlin очень краткий, но при этом выразительный язык",
                    note = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.BLUE),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    summary = "Kotlin очень краткий, но при этом выразительный язык",
                    note = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.GREEN),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    summary = "Kotlin очень краткий, но при этом выразительный язык",
                    note = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.PINK),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    summary = "Kotlin очень краткий, но при этом выразительный язык",
                    note = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.RED),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    summary = "Kotlin очень краткий, но при этом выразительный язык",
                    note = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.YELLOW),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    summary = "Kotlin очень краткий, но при этом выразительный язык",
                    note = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.VIOLET)
    )


}