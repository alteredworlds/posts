package com.example.twcgilbert.postsapp.repo.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.twcgilbert.postsapp.repo.persistence.model.CommentEntity
import com.example.twcgilbert.postsapp.repo.persistence.model.PostEntity
import com.example.twcgilbert.postsapp.repo.persistence.model.UserEntity

@Database(entities = arrayOf(CommentEntity::class, UserEntity::class, PostEntity::class), version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun commentDao(): CommentDao

    abstract fun postDao(): PostDao
}