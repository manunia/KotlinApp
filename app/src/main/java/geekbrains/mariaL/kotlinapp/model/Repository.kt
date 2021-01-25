package geekbrains.mariaL.kotlinapp.model

import androidx.lifecycle.MutableLiveData
import java.util.*

object Repository {
    private val notesLiveData = MutableLiveData<List<Note>>()

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

    init {
        notesLiveData.value = notes
    }

    fun getNotes(): MutableLiveData<List<Note>> = notesLiveData

    fun saveNote(note: Note) {
        addOrReplace(note)
        notesLiveData.value = notes
    }

    private fun addOrReplace(note: Note) {
        for (i in 0 until notes.size) {
            if (notes[i] == note) {
                notes[i] = note
                return
            }
        }

        notes.add(note)
    }


}