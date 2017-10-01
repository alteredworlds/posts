package com.example.twcgilbert.postsapp.io

import com.example.twcgilbert.postsapp.io.data.Comment
import com.example.twcgilbert.postsapp.io.data.Post
import com.example.twcgilbert.postsapp.io.data.User
import com.example.twcgilbert.postsapp.io.network.PostsService
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import timber.log.Timber

/**
 * Created by twcgilbert on 01/10/2017.
 */
class DataRepositoryImpl(private var api: PostsService) : DataRepository {

    override fun getUsers(): Observable<List<User>> = api.getUsers().cache()

    override fun getPosts(): Observable<List<Post>> {
        return api
                .getPosts()
                .zipWith(getUsers(), BiFunction { posts, users ->
                    val resultList = ArrayList<Post>(posts.size)
                    for (post in posts) {
                        val user = users.filter { it.id == post.userId }.single()
                        resultList.add(Post(
                                post.userId,
                                post.id,
                                post.title,
                                post.body,
                                user.name,
                                user.email)
                        )
                    }
                    resultList
                })
    }

    override fun getComments(postId: Int): Observable<List<Comment>> = api.getComments(postId)
}