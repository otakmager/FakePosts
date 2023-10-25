package com.otakmager.core.data

import com.otakmager.core.data.local.LocalDataSource
import com.otakmager.core.data.remote.RemoteDataSource
import com.otakmager.core.data.remote.network.ApiResponse
import com.otakmager.core.data.remote.response.DetailPostsResponse
import com.otakmager.core.domain.model.Posts
import com.otakmager.core.domain.repository.IPostsRepository
import com.otakmager.core.utils.AppExecutors
import com.otakmager.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IPostsRepository {

    override fun getAllPosts(): Flow<Resource<List<Posts>>> =
        object : NetworkBoundResource<List<Posts>, List<DetailPostsResponse>>() {
            override fun loadFromDB(): Flow<List<Posts>> {
                return localDataSource.getAllPosts().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Posts>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<DetailPostsResponse>>> =
                remoteDataSource.getAllPosts()

            override suspend fun saveCallResult(data: List<DetailPostsResponse>) {
                val postsList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertPosts(postsList)
            }
        }.asFlow()

    override fun getFavoritePosts(): Flow<List<Posts>> {
        return localDataSource.getFavoritePosts().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoritePosts(posts: Posts, state: Boolean) {
        val postsEntity = DataMapper.mapDomainToEntity(posts)
        appExecutors.diskIO().execute { localDataSource.setFavoritePosts(postsEntity, state) }
    }
}