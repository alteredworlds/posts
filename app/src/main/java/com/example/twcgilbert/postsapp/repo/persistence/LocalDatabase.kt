package com.example.twcgilbert.postsapp.repo.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.twcgilbert.postsapp.repo.persistence.model.CommentEntity
import com.example.twcgilbert.postsapp.repo.persistence.model.PostEntity
import com.example.twcgilbert.postsapp.repo.persistence.model.UserEntity

@Database(entities = arrayOf(CommentEntity::class, UserEntity::class, PostEntity::class), version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun commentDao(): CommentDao

    abstract fun postDao(): PostDao
}