package ru.kazakova_net.notelin.di

import dagger.Module
import dagger.Provides
import ru.kazakova_net.notelin.mvp.models.NoteDAO
import javax.inject.Singleton

@Module
class NoteModelModule {

    @Provides
    @Singleton
    fun provideNoteModel(): NoteDAO = NoteDAO()
}