package com.otakmager.core.data.remote.network

import com.otakmager.core.data.remote.response.DetailPostsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getList(): List<DetailPostsResponse>
}