package com.otakmager.core.di

import android.content.Context
import androidx.room.Room
import com.otakmager.core.data.local.room.PostsDao
import com.otakmager.core.data.local.room.PostsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PostsDatabase = Room.databaseBuilder(
        context,
        PostsDatabase::class.java, "Posts.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: PostsDatabase): PostsDao = database.postsDao()
}