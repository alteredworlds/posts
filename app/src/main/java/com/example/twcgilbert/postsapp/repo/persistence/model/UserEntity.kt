package com.example.twcgilbert.postsapp.repo.persistence.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
        @PrimaryKey val id: Int,
        val name: String? = null,
        val username: String? = null,
        val email: String? = null)