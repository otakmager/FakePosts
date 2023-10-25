package com.otakmager.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.otakmager.core.domain.usecase.PostsUseCase

class FavoriteViewModel(postsUseCase: PostsUseCase) : ViewModel() {
    val favoritePosts = postsUseCase.getFavoritePosts().asLiveData()
}