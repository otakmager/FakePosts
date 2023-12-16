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
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PostsDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("qwerty-posts".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context.applicationContext,
            PostsDatabase::class.java,
            "Posts.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun providePostsDao(database: PostsDatabase): PostsDao = database.postsDao()
}