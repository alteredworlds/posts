package com.example.twcgilbert.postsapp.repo.persistence.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "posts",
        foreignKeys = arrayOf(
                ForeignKey(
                        entity = UserEntity::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("userId"),
                        onDelete = ForeignKey.CASCADE
                )),
        indices = arrayOf(Index("userId"))
)
data class PostEntity(
        @PrimaryKey val id: Int,
        var userId: Int,
        var title: String?,
        var body: String?)