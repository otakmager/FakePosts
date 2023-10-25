package com.otakmager.core.utils

import com.otakmager.core.data.local.entity.PostsEntity
import com.otakmager.core.data.remote.response.DetailPostsResponse
import com.otakmager.core.domain.model.Posts

object DataMapper {
    fun mapResponsesToEntities(input: List<DetailPostsResponse>): List<PostsEntity> {
        val postsList = ArrayList<PostsEntity>()
        input.map {
            val post = PostsEntity(
                id = it.id,
                title = it.title,
                body = it.body,
                isFavorite = false
            )
            postsList.add(post)
        }
        return postsList
    }

    fun mapEntitiesToDomain(input: List<PostsEntity>): List<Posts> =
        input.map {
            Posts(
                id = it.id,
                title = it.title,
                body = it.body,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Posts) = PostsEntity(
        id = input.id,
        title = input.title,
        body = input.body,
        isFavorite = input.isFavorite
    )
}