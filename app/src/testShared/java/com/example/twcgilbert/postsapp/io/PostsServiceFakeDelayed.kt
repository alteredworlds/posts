package com.example.twcgilbert.postsapp.io

import com.example.twcgilbert.postsapp.io.PostsServiceFakeDelayed.companion.commentsDelay
import com.example.twcgilbert.postsapp.io.PostsServiceFakeDelayed.companion.postsDelay
import com.example.twcgilbert.postsapp.io.PostsServiceFakeDelayed.companion.usersDelay
import com.example.twcgilbert.postsapp.io.data.Comment
import com.example.twcgilbert.postsapp.io.data.SimplePost
import com.example.twcgilbert.postsapp.io.data.User
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

/**
 * Created by twcgilbert on 23/10/2017.
 */
class PostsServiceFakeDelayed : PostsServiceFake() {
    object companion {
        const val postsDelay = 1L
        const val usersDelay = 3L
        const val commentsDelay = 6L
    }

    override fun getPosts(): Observable<List<SimplePost>> {
        return Observable
                .interval(postsDelay, TimeUnit.SECONDS)
                .zipWith(super.getPosts(), BiFunction { tick, posts -> posts })
    }

    override fun getUsers(): Observable<List<User>> {
        return Observable
                .interval(usersDelay, TimeUnit.SECONDS)
                .zipWith(super.getUsers(), BiFunction { tick, users -> users })
    }

    override fun getComments(postId: Int): Observable<List<Comment>> {
        return Observable
                .interval(commentsDelay, TimeUnit.SECONDS)
                .zipWith(super.getComments(postId), BiFunction { tick, comments -> comments })
    }
}