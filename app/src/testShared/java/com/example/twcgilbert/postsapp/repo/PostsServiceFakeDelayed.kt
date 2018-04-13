package com.example.twcgilbert.postsapp.repo

import com.example.twcgilbert.postsapp.repo.data.Comment
import com.example.twcgilbert.postsapp.repo.data.SimplePost
import com.example.twcgilbert.postsapp.repo.data.User
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

/**
 * Created by twcgilbert on 23/10/2017.
 */
class PostsServiceFakeDelayed : PostsServiceFake() {

    companion object {
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