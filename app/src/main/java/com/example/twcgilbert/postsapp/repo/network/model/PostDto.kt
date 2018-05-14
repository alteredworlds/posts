package com.example.twcgilbert.postsapp.repo.network.model

/**
 * Created by twcgilbert on 01/10/2017.
 */
data class PostDto(
        val userId: Int,
        val id: Int,
        val title: String?,
        val body: String?
)