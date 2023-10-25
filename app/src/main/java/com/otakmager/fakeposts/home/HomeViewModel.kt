package com.otakmager.fakeposts.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.otakmager.core.domain.usecase.PostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(postsUseCase: PostsUseCase) : ViewModel() {
    val posts = postsUseCase.getAllPosts().asLiveData()
}