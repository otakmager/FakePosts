package com.otakmager.core.domain.repository

import com.otakmager.core.data.Resource
import com.otakmager.core.domain.model.Posts
import kotlinx.coroutines.flow.Flow

interface IPostsRepository {
    fun getAllPosts(): Flow<Resource<List<Posts>>>

    fun getFavoritePosts(): Flow<List<Posts>>

    fun setFavoritePosts(posts: Posts, state: Boolean)
}