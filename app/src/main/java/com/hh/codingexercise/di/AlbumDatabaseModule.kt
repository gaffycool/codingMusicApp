package com.hh.codingexercise.di

import android.app.Application
import com.hh.codingexercise.data.storage.AlbumDao
import com.hh.codingexercise.data.storage.AlbumDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AlbumDatabaseModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): AlbumDatabase = AlbumDatabase.buildDefault(app)

    @Singleton
    @Provides
    fun provideUserDao(db: AlbumDatabase): AlbumDao = db.albumDao()
}