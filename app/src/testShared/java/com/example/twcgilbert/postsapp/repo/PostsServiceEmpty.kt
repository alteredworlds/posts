package com.example.twcgilbert.postsapp.repo

import com.example.twcgilbert.postsapp.repo.data.Comment
import com.example.twcgilbert.postsapp.repo.data.SimplePost
import com.example.twcgilbert.postsapp.repo.data.User
import com.example.twcgilbert.postsapp.repo.network.PostsService
import io.reactivex.Single

/**
 * Created by twcgilbert on 23/10/2017.
 */
class PostsServiceEmpty : PostsService {

    override fun getPosts(): Single<List<SimplePost>> = Single.just(ArrayList())

    override fun getUsers(): Single<List<User>> = Single.just(ArrayList())

    override fun getComments(postId: Int): Single<List<Comment>> = Single.just(ArrayList())
}