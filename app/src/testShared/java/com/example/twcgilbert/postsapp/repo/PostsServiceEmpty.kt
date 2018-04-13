package com.example.twcgilbert.postsapp.repo

import com.example.twcgilbert.postsapp.repo.data.Comment
import com.example.twcgilbert.postsapp.repo.data.SimplePost
import com.example.twcgilbert.postsapp.repo.data.User
import com.example.twcgilbert.postsapp.repo.network.PostsService
import io.reactivex.Observable

/**
 * Created by twcgilbert on 23/10/2017.
 */
class PostsServiceEmpty : PostsService {

    override fun getPosts(): Observable<List<SimplePost>> = Observable.just(ArrayList())

    override fun getUsers(): Observable<List<User>> = Observable.just(ArrayList())

    override fun getComments(postId: Int): Observable<List<Comment>> = Observable.just(ArrayList())
}