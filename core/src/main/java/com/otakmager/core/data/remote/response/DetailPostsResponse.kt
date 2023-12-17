package com.otakmager.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailPostsResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("body")
    val body: String
)
