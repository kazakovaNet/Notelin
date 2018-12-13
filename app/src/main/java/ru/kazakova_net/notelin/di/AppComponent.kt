package ru.kazakova_net.notelin.di

import dagger.Component
import ru.kazakova_net.notelin.mvp.presenters.MainPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NoteModelModule::class))
interface AppComponent {

    fun inject(mainPresenter: MainPresenter)
}