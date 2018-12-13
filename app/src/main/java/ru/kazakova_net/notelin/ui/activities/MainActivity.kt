package ru.kazakova_net.notelin.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.kazakova_net.notelin.R
import ru.kazakova_net.notelin.mvp.common.MvpAppCompatActivity
import ru.kazakova_net.notelin.mvp.models.Note
import ru.kazakova_net.notelin.mvp.presenters.MainPresenter
import ru.kazakova_net.notelin.mvp.views.MainView
import ru.kazakova_net.notelin.ui.adapters.NotesAdapter
import ru.kazakova_net.notelin.ui.commons.ItemClickSupport

class MainActivity : MvpAppCompatActivity(), MainView {
    override fun onNotesLoaded(notes: List<Note>) {
        notesList.adapter = NotesAdapter(notes)
        updateView()
    }

    override fun updateView() {
        notesList.adapter.notifyDataSetChanged()
        if (notesList.adapter.itemCount == 0) {
            notesList.visibility = View.GONE
            notesIsEmptyTxt.visibility = View.VISIBLE
        } else {
            notesList.visibility = View.VISIBLE
            notesIsEmptyTxt.visibility = View.GONE
        }
    }

    override fun onSearchResult(notes: List<Note>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAllNotesDeleted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNoteDeleted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoteInfoDialog(noteInfo: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideNoteInfoDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoteDeleteDialog(notePosition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideNoteDeleteDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoteContextDialog(notePosition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideNoteContextDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabButton.setOnClickListener { view ->
            presenter.openNewNote(this)
        }

        with(ItemClickSupport.addTo(notesList)) {
            setOnItemClickListener { recyclerView, position, view -> presenter.openNote(this@MainActivity, position) }
            setOnItemLongClickListener { recyclerView, position, view -> presenter.showNoteContextDialog(position); true }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
//            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
