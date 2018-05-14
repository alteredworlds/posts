package com.example.twcgilbert.postsapp.repo.network

import com.example.twcgilbert.postsapp.repo.network.model.CommentDto
import com.example.twcgilbert.postsapp.repo.network.model.PostDto
import com.example.twcgilbert.postsapp.repo.network.model.UserDto
import io.reactivex.Single

/**
 * Created by twcgilbert on 23/10/2017.
 */
class PostsServiceEmpty : PostsService {

    override fun getPosts(): Single<List<PostDto>> = Single.just(ArrayList())

    override fun getUsers(): Single<List<UserDto>> = Single.just(ArrayList())

    override fun getComments(postId: Int): Single<List<CommentDto>> = Single.just(ArrayList())
}