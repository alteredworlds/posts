package com.example.twcgilbert.postsapp.repo.network.model

/**
 * Created by twcgilbert on 01/10/2017.
 */
data class CommentDto(
        val postId: Int,
        val id: Int,
        val name: String?,
        val email: String?,
        val body: String?)