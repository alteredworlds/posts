package com.example.twcgilbert.postsapp.repo.persistence.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

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