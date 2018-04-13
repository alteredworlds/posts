package com.example.twcgilbert.postsapp.repo

import com.example.twcgilbert.postsapp.repo.data.Comment
import com.example.twcgilbert.postsapp.repo.data.SimplePost
import com.example.twcgilbert.postsapp.repo.data.User
import io.reactivex.Single
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

    override fun getPosts(): Single<List<SimplePost>> {
        return super.getPosts()
                .delay(postsDelay, TimeUnit.SECONDS)
    }

    override fun getUsers(): Single<List<User>> {
        return super.getUsers()
                .delay(usersDelay, TimeUnit.SECONDS)
    }

    override fun getComments(postId: Int): Single<List<Comment>> {
        return super.getComments(postId)
                .delay(commentsDelay, TimeUnit.SECONDS)
    }
}