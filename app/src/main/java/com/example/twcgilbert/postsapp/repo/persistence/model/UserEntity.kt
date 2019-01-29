package com.example.twcgilbert.postsapp.repo.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
        @PrimaryKey val id: Int,
        val name: String? = null,
        val username: String? = null,
        val email: String? = null)