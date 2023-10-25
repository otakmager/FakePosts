package com.otakmager.fakeposts.detail

import androidx.lifecycle.ViewModel
import com.otakmager.core.domain.model.Posts
import com.otakmager.core.domain.usecase.PostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPostsViewModel @Inject constructor(private val postsUseCase: PostsUseCase) :
    ViewModel() {
    fun setFavoritePosts(posts: Posts, newStatus: Boolean) =
        postsUseCase.setFavoritePosts(posts, newStatus)
}