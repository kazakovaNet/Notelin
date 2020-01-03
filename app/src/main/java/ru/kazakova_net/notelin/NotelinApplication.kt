package ru.kazakova_net.notelin

import android.app.Application
import android.content.Context
import ru.kazakova_net.notelin.di.AppComponent
import ru.kazakova_net.notelin.di.DaggerAppComponent
import ru.kazakova_net.notelin.di.NoteModelModule
import ru.kazakova_net.notelin.utils.initPrefs

class NotelinApplication : Application() {

    companion object {
        lateinit var graph: AppComponent
        lateinit var context:Context
    }

    override fun onCreate() {
        super.onCreate()

        initPrefs(this)

        context = this

        graph = DaggerAppComponent.builder().noteModelModule(NoteModelModule()).build()
    }
}