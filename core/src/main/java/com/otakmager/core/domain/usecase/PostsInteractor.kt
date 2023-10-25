package com.otakmager.core.domain.usecase

import com.otakmager.core.domain.model.Posts
import com.otakmager.core.domain.repository.IPostsRepository
import javax.inject.Inject

class PostsInteractor @Inject constructor(private val postsRepository: IPostsRepository) :
    PostsUseCase {

    override fun getAllPosts() = postsRepository.getAllPosts()

    override fun getFavoritePosts() = postsRepository.getFavoritePosts()

    override fun setFavoritePosts(posts: Posts, state: Boolean) =
        postsRepository.setFavoritePosts(posts, state)
}