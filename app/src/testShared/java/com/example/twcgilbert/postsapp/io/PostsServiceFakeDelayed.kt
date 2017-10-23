package com.example.twcgilbert.postsapp.io

import com.example.twcgilbert.postsapp.io.data.SimplePost
import com.example.twcgilbert.postsapp.io.data.User
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

/**
 * Created by twcgilbert on 23/10/2017.
 */
class PostsServiceFakeDelayed : PostsServiceFake() {
    override fun getPosts(): Observable<List<SimplePost>> {
        return Observable
                .interval(2, TimeUnit.SECONDS)
                .zipWith(super.getPosts(), BiFunction { tick, posts -> posts })
    }

    override fun getUsers(): Observable<List<User>> {
        return Observable
                .interval(3, TimeUnit.SECONDS)
                .zipWith(super.getUsers(), BiFunction { tick, users -> users })
    }
}