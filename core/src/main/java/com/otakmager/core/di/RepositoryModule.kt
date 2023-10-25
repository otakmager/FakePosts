package com.otakmager.core.di

import com.otakmager.core.data.PostsRepository
import com.otakmager.core.domain.repository.IPostsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(postsRepository: PostsRepository): IPostsRepository

}