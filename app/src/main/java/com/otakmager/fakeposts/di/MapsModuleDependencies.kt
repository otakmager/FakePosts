package com.otakmager.fakeposts.di

import com.otakmager.core.domain.usecase.PostsUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MapsModuleDependencies {

    fun postsUseCase(): PostsUseCase
}