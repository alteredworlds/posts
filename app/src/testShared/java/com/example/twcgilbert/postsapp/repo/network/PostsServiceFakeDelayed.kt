package com.example.twcgilbert.postsapp.repo.network

import com.example.twcgilbert.postsapp.repo.network.model.CommentDto
import com.example.twcgilbert.postsapp.repo.network.model.PostDto
import com.example.twcgilbert.postsapp.repo.network.model.UserDto
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

    override fun getPosts(): Single<List<PostDto>> {
        return super.getPosts()
                .delay(postsDelay, TimeUnit.SECONDS)
    }

    override fun getUsers(): Single<List<UserDto>> {
        return super.getUsers()
                .delay(usersDelay, TimeUnit.SECONDS)
    }

    override fun getComments(postId: Int): Single<List<CommentDto>> {
        return super.getComments(postId)
                .delay(commentsDelay, TimeUnit.SECONDS)
    }
}