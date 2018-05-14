package com.example.twcgilbert.postsapp.repo.network

import com.example.twcgilbert.postsapp.repo.network.model.CommentDto
import com.example.twcgilbert.postsapp.repo.network.model.UserDto
import io.reactivex.Single

/**
 * Created by twcgilbert on 23/10/2017.
 */
open class PostsServiceFake : PostsService {

    override fun getPosts(): Single<List<com.example.twcgilbert.postsapp.repo.network.model.PostDto>> {
        return Single.just(DtoTestConstants.posts)
    }

    override fun getUsers(): Single<List<UserDto>> {
        return Single.just(DtoTestConstants.users)
    }

    override fun getComments(postId: Int): Single<List<CommentDto>> {
        return Single.just(DtoTestConstants.comments.filter { it.postId == postId })
    }
}