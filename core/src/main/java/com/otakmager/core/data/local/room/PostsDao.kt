package com.otakmager.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.otakmager.core.data.local.entity.PostsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostsDao {
    @Query("SELECT * FROM posts")
    fun getAllPosts(): Flow<List<PostsEntity>>

    @Query("SELECT * FROM posts where isFavorite = 1")
    fun getFavoritePosts(): Flow<List<PostsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostsEntity>)

    @Update
    fun updateFavoritePosts(posts: PostsEntity)
}