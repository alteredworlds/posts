package com.example.twcgilbert.postsapp.io.network

import com.example.twcgilbert.postsapp.io.data.Comment
import com.example.twcgilbert.postsapp.io.data.Post
import com.example.twcgilbert.postsapp.io.data.SimplePost
import com.example.twcgilbert.postsapp.io.data.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface PostsService {
    @GET("posts")
    fun getPosts(): Observable<List<SimplePost>>

    @GET("users")
    fun getUsers(): Observable<List<User>>

    @GET("comments")
    fun getComments(@Query("postId") postId: Int): Observable<List<Comment>>
}