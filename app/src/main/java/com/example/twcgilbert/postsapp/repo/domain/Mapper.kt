package com.example.twcgilbert.postsapp.repo.domain

import com.example.twcgilbert.postsapp.repo.network.model.CommentDto
import com.example.twcgilbert.postsapp.repo.network.model.PostDto
import com.example.twcgilbert.postsapp.repo.network.model.UserDto
import com.example.twcgilbert.postsapp.repo.persistence.model.CommentEntity
import com.example.twcgilbert.postsapp.repo.persistence.model.PostEntity
import com.example.twcgilbert.postsapp.repo.persistence.model.UserEntity
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapUserDtos(userDtos: List<UserDto>): List<UserEntity> =
            userDtos.map { map(it) }

    fun map(userDto: UserDto): UserEntity =
            UserEntity(userDto.id, userDto.name, userDto.username, userDto.email)

    fun mapPostDtos(postDtos: List<PostDto>): List<PostEntity> =
            postDtos.map { map(it) }

    fun map(postDto: PostDto): PostEntity =
            PostEntity(postDto.id, postDto.userId, postDto.title, postDto.body)

    fun mapCommentDtos(commentDtos: List<CommentDto>): List<CommentEntity> =
            commentDtos.map { map(it) }

    fun map(commentDto: CommentDto): CommentEntity =
            CommentEntity(commentDto.id, commentDto.postId, commentDto.name, commentDto.email, commentDto.body)
}