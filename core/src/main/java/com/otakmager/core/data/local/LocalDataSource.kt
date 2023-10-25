package com.otakmager.core.data.local

import com.otakmager.core.data.local.entity.PostsEntity
import com.otakmager.core.data.local.room.PostsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val postsDao: PostsDao) {

    fun getAllPosts(): Flow<List<PostsEntity>> = postsDao.getAllPosts()

    fun getFavoritePosts(): Flow<List<PostsEntity>> = postsDao.getFavoritePosts()

    suspend fun insertPosts(postsList: List<PostsEntity>) = postsDao.insertPosts(postsList)

    fun setFavoritePosts(posts: PostsEntity, newState: Boolean) {
        posts.isFavorite = newState
        postsDao.updateFavoritePosts(posts)
    }
}