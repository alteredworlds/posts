package com.example.twcgilbert.postsapp.repo

import com.example.twcgilbert.postsapp.repo.data.Comment
import com.example.twcgilbert.postsapp.repo.data.Post
import com.example.twcgilbert.postsapp.repo.data.User
import io.reactivex.Single

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface DataRepository {

    fun getPosts(): Single<List<Post>>

    fun getUsers(): Single<List<User>>

    fun getComments(postId: Int): Single<List<Comment>>
}