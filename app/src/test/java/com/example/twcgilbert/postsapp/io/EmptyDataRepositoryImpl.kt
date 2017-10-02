package com.example.twcgilbert.postsapp.io

import com.example.twcgilbert.postsapp.io.data.Comment
import com.example.twcgilbert.postsapp.io.data.Post
import com.example.twcgilbert.postsapp.io.data.User
import io.reactivex.Observable

/**
 * Created by twcgilbert on 02/10/2017.
 */
class EmptyDataRepositoryImpl : DataRepository {
    override fun getPosts(): Observable<List<Post>> {
        return Observable.just(ArrayList<Post>())
    }

    override fun getUsers(): Observable<List<User>> {
        return Observable.just(ArrayList<User>())
    }

    override fun getComments(postId: Int): Observable<List<Comment>> {
        return Observable.just(ArrayList<Comment>())
    }
}