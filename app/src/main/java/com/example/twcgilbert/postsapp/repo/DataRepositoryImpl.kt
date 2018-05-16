package com.example.twcgilbert.postsapp.repo

import com.example.twcgilbert.postsapp.repo.persistence.LocalDatabase

/**
 * Created by twcgilbert on 01/10/2017.
 */
class DataRepositoryImpl(private val db: LocalDatabase) : DataRepository {

    override fun getPosts() = db.postDao()
            .getAllPostsPaged()

    override fun getNumComments(postId: Int) = db.commentDao()
            .getNumCommentsForPostId(postId)
}