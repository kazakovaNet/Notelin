package ru.kazakova_net.notelin.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.kazakova_net.notelin.R
import ru.kazakova_net.notelin.mvp.models.Note
import ru.kazakova_net.notelin.utils.formatDate

class NotesAdapter(list: List<Note>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private var notesList: List<Note> = list

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.note_item_layout, viewGroup, false);
        return ViewHolder(v);
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val note = notesList[i];
        viewHolder.title.text = note.title;

        viewHolder.date.text = formatDate(note.changeDate);
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.itemNoteTitleTxt) as TextView
        var date: TextView = itemView.findViewById(R.id.itemNoteDateTxt) as TextView
    }
}