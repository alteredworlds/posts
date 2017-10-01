package com.example.twcgilbert.postsapp.io

import com.example.twcgilbert.postsapp.io.data.Comment
import com.example.twcgilbert.postsapp.io.data.Post
import com.example.twcgilbert.postsapp.io.data.User
import io.reactivex.Observable

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface DataRepository {

    fun getPosts(): Observable<List<Post>>

    fun getUsers(): Observable<List<User>>

    fun getComments(postId: Int): Observable<List<Comment>>
}