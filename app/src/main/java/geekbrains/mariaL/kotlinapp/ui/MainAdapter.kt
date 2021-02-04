package geekbrains.mariaL.kotlinapp.ui

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import geekbrains.mariaL.kotlinapp.R
import geekbrains.mariaL.kotlinapp.databinding.ItemNoteBinding
import geekbrains.mariaL.kotlinapp.model.Color
import geekbrains.mariaL.kotlinapp.model.Note

interface OnItemClickListener {
    fun onItemClick(note: Note)
}

class MainAdapter(private val onClick: OnItemClickListener) : RecyclerView.Adapter<MainAdapter.NoteViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ui: ItemNoteBinding = ItemNoteBinding.bind(itemView)
        fun bind(note: Note) {
            with(note) {
                ui.title.text = this.title
                ui.body.text = this.note?.substring(0, note.note.length / 3)

                ui.container.setCardBackgroundColor(note.color.getColorInt(itemView.context))
                itemView.setOnClickListener { onClick.onItemClick(note) }
            }
        }
    }
}