package ru.kazakova_net.notelin.mvp.presenters

import android.app.Activity
import android.content.Intent
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.kazakova_net.notelin.NotelinApplication
import ru.kazakova_net.notelin.mvp.models.Note
import ru.kazakova_net.notelin.mvp.models.NoteDAO
import ru.kazakova_net.notelin.mvp.views.MainView
import ru.kazakova_net.notelin.ui.activities.NoteActivity
import ru.kazakova_net.notelin.utils.getNotesSortMethodName
import ru.kazakova_net.notelin.utils.setNotesSortMethod
import java.util.*
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var noteDAO: NoteDAO
    lateinit var notesList: MutableList<Note>

    init {
        NotelinApplication.graph.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadAllNotes()
    }

    fun loadAllNotes() {
        val notesList = noteDAO.loadAllNotes()
        viewState.onNotesLoaded(notesList);
    }

    fun openNote(activity: Activity, position: Int) {
        val intent = Intent(activity, NoteActivity::class.java)
        intent.putExtra("note_id", notesList[position].id)
    }

    fun showNoteContextDialog(position: Int) {
        viewState.showNoteContextDialog(position)
    }

    fun hideNoteContextDialog() {
        viewState.hideNoteContextDialog()
    }

    fun openNewNote(activity: Activity) {
        val newNote = noteDAO.createNote()
        notesList.add(newNote)
        sortNotesBy(getCurrentSortMethod())
        openNote(activity, notesList.indexOf(newNote))
    }

    private fun getCurrentSortMethod(): SortNotesBy {
        val defaultSortMethodName = SortNotesBy.DATE.toString()
        val currentSortMethodName = getNotesSortMethodName(defaultSortMethodName)
        return SortNotesBy.valueOf(currentSortMethodName)
    }

    private fun sortNotesBy(sortMethod: SortNotesBy) {
        notesList.sortWith(sortMethod)
        setNotesSortMethod(sortMethod.toString())
        viewState.updateView()
    }

    enum class SortNotesBy : Comparator<Note> {
        DATE {
            override fun compare(lhs: Note, rhs: Note): Int = lhs.changeDate!!.compareTo(rhs.changeDate)
        },
        NAME {
            override fun compare(lhs: Note, rhs: Note): Int = lhs.title!!.compareTo(rhs.title!!)
        }
    }
}