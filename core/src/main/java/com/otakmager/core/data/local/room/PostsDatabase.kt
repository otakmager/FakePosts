package com.otakmager.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.otakmager.core.data.local.entity.PostsEntity

@Database(entities = [PostsEntity::class], version = 1, exportSchema = false)
abstract class PostsDatabase : RoomDatabase() {

    abstract fun postsDao(): PostsDao

}