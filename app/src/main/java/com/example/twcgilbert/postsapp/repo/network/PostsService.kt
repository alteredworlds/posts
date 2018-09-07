package com.example.twcgilbert.postsapp.repo.network

import com.example.twcgilbert.postsapp.repo.network.model.CommentDto
import com.example.twcgilbert.postsapp.repo.network.model.PostDto
import com.example.twcgilbert.postsapp.repo.network.model.UserDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface PostsService {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        const val POSTS = "posts"

        const val USERS = "users"

        const val COMMENTS = "comments"

        const val POSTID = "postId"
    }

    @GET(POSTS)
    fun getPosts(): Single<List<PostDto>>

    @GET(USERS)
    fun getUsers(): Single<List<UserDto>>

    @GET(COMMENTS)
    fun getComments(@Query(POSTID) postId: Int): Single<List<CommentDto>>
}