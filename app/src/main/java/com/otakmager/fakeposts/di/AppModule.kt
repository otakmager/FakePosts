package com.otakmager.fakeposts.di

import com.otakmager.core.domain.usecase.PostsInteractor
import com.otakmager.core.domain.usecase.PostsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun providePostsUseCase(postsInteractor: PostsInteractor): PostsUseCase

}