package com.example.twcgilbert.postsapp.repo

import com.example.twcgilbert.postsapp.repo.data.Post
import com.example.twcgilbert.postsapp.repo.network.PostsService
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/**
 * Created by twcgilbert on 01/10/2017.
 */
class DataRepositoryImpl(private var api: PostsService) : DataRepository {

    override fun getUsers() = api.getUsers().cache()

    override fun getPosts(): Observable<List<Post>> {
        return api
                .getPosts()
                .zipWith(getUsers(), BiFunction { posts, users ->
                    val resultList = ArrayList<Post>(posts.size)
                    for (post in posts) {
                        val user = users.single{ it.id == post.userId }
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

    override fun getComments(postId: Int) = api.getComments(postId)
}