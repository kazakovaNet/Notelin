package ru.kazakova_net.notelin.mvp.models

import com.activeandroid.query.Delete
import com.activeandroid.query.Select
import java.util.*

class NoteDAO {
    fun createNote(): Note {
        val note = Note("Новая заметка", Date())

        note.save()

        return note
    }

    fun saveNote(note: Note) = note.save()

    fun loadAllNotes() = Select().from(Note::class.java).execute<Note>()

    fun getNoteById(noteId: Long) = Select().from(Note::class.java).where("id = ?", noteId).executeSingle<Note>()

    fun deleteAllNotes() {
        Delete().from(Note::class.java).execute<Note>()
    }

    fun deleteNote(note: Note) = note.delete()
}