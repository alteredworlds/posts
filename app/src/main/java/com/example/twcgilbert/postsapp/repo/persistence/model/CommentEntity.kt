package com.example.twcgilbert.postsapp.repo.persistence.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "comments",
        foreignKeys = arrayOf(
                ForeignKey(
                        entity = PostEntity::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("postId"),
                        onDelete = ForeignKey.CASCADE
                )),
        indices = arrayOf(Index("postId"))
)
data class CommentEntity(
        @PrimaryKey val id: Int,
        val postId: Int,
        val name: String?,
        val email: String?,
        val body: String?)