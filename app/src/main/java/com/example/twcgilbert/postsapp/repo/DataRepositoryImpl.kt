package com.example.twcgilbert.postsapp.repo

import com.example.twcgilbert.postsapp.repo.data.Post
import com.example.twcgilbert.postsapp.repo.network.PostsService
import io.reactivex.Single
import io.reactivex.functions.BiFunction

/**
 * Created by twcgilbert on 01/10/2017.
 */
class DataRepositoryImpl(private var api: PostsService) : DataRepository {

    override fun getUsers() = api.getUsers().cache()

    override fun getPosts(): Single<List<Post>> = api
            .getPosts()
            .zipWith(getUsers(), BiFunction { posts, users ->
                posts.map { post ->
                    val user = users.single { it.id == post.userId }
                    Post(
                            post.userId,
                            post.id,
                            post.title,
                            post.body,
                            user.name,
                            user.email,
                            PostsService.getImageUrl(user.email))
                }
            })

    override fun getComments(postId: Int) = api.getComments(postId)
}