package com.example.twcgilbert.postsapp.repo

import com.example.twcgilbert.postsapp.repo.data.Comment
import com.example.twcgilbert.postsapp.repo.data.Post
import com.example.twcgilbert.postsapp.repo.data.SimplePost
import com.example.twcgilbert.postsapp.repo.data.User
import com.example.twcgilbert.postsapp.repo.network.PostsService
import io.reactivex.Observable

/**
 * Created by twcgilbert on 23/10/2017.
 */
open class PostsServiceFake : PostsService {

    companion object {

        const val userId1 = 1
        const val userId1Username = "Username1"
        const val userId1Email = "test1@test.com"

        const val userId2 = 2
        const val userId2Username = "Username2"
        const val userId2Email = "test2@test.com"

        const val postId1 = 1
        const val post1Title = "Hi1"
        const val post1Body = "Body1"

        const val postId2 = 2
        const val post2Title = "Hi2"
        const val post2Body = "Body2"

        @JvmStatic
        val testSimplePost1 = SimplePost(userId1,
                postId1,
                post1Title,
                post1Body)

        @JvmStatic
        val testSimplePost2 = SimplePost(userId2,
                postId2,
                post2Title,
                post2Body)

        @JvmStatic
        val testPost1 = Post(userId1,
                postId1,
                post1Title,
                post1Body,
                userId1Username,
                userId1Email)

        @JvmStatic
        val testPost2 = Post(userId2,
                postId2,
                post2Title,
                post2Body,
                userId2Username,
                userId2Email)
    }

    override fun getPosts(): Observable<List<SimplePost>> {
        val posts = arrayListOf(
                testSimplePost1,
                testSimplePost2,
                SimplePost(userId1, 3, "Hi3", "Body"),
                SimplePost(userId1, 4, "Hi4", "Body"),
                SimplePost(userId1, 5, "Hi5", "Body"),
                SimplePost(userId1, 6, "Hi6", "Body"),
                SimplePost(userId1, 7, "Hi7", "Body"),
                SimplePost(userId1, 8, "Hi8", "Body"),
                SimplePost(userId2, 9, "Hi9", "Body"),
                SimplePost(userId2, 10, "Hi10", "Body"),
                SimplePost(userId2, 11, "Hi11", "Body"),
                SimplePost(userId2, 12, "Hi12", "Body"),
                SimplePost(userId2, 13, "Hi13", "Body"),
                SimplePost(userId2, 14, "Hi14", "Body"),
                SimplePost(userId2, 15, "Hi15", "Body"),
                SimplePost(userId2, 16, "Hi16", "Body")
        )
        return Observable.just(posts)
    }

    override fun getUsers(): Observable<List<User>> {
        val users = arrayListOf(
                User(userId1,
                        "Tom",
                        userId1Username,
                        userId1Email),
                User(userId2,
                        "Tim",
                        userId2Username,
                        userId2Email)
        )
        return Observable.just(users)
    }

    override fun getComments(postId: Int): Observable<List<Comment>> {
        val comments = arrayListOf(
                Comment(postId1,
                        1,
                        "Comment1",
                        "fred@fred.com",
                        "Body1")
        )
        return Observable.just(comments.filter { it.postId == postId })
    }
}