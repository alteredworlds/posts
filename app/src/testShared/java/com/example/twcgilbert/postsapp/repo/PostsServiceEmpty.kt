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
    override fun getPosts(): Observable<List<SimplePost>> {
        return Observable.just(ArrayList<SimplePost>())
    }

    override fun getUsers(): Observable<List<User>> {
        return Observable.just(ArrayList<User>())
    }

    override fun getComments(postId: Int): Observable<List<Comment>> {
        return Observable.just(ArrayList<Comment>())
    }
}