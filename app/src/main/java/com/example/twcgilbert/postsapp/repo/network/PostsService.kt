package com.example.twcgilbert.postsapp.repo.network

import com.example.twcgilbert.postsapp.repo.data.Comment
import com.example.twcgilbert.postsapp.repo.data.SimplePost
import com.example.twcgilbert.postsapp.repo.data.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface PostsService {

    companion object {
        const val BASE_URL = "http://jsonplaceholder.typicode.com/"

        const val ADORABLE_URL = "https://api.adorable.io/avatars/128/"

        const val IMAGE_EXTENSION = ".png"

        const val POSTS = "posts"

        const val USERS = "users"

        const val COMMENTS = "comments"

        const val POSTID = "postId"

        fun getImageUrl(userEmail: String?): String = ADORABLE_URL + userEmail + IMAGE_EXTENSION
    }

    @GET(POSTS)
    fun getPosts(): Single<List<SimplePost>>

    @GET(USERS)
    fun getUsers(): Single<List<User>>

    @GET(COMMENTS)
    fun getComments(@Query(POSTID) postId: Int): Single<List<Comment>>
}