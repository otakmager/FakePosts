package com.otakmager.core.data.remote

import android.util.Log
import com.otakmager.core.data.remote.network.ApiResponse
import com.otakmager.core.data.remote.network.ApiService
import com.otakmager.core.data.remote.response.DetailPostsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllPosts(): Flow<ApiResponse<List<DetailPostsResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getList()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
