package com.example.twcgilbert.postsapp.repo

import androidx.paging.DataSource
import com.example.twcgilbert.postsapp.repo.model.Post
import io.reactivex.Flowable

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface DataRepository {

    fun getPosts(): DataSource.Factory<Int, Post>

    fun getNumComments(postId: Int): Flowable<Int>
}