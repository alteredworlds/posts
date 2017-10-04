package com.example.twcgilbert.postsapp.io

import com.example.twcgilbert.postsapp.io.data.*
import io.reactivex.Observable

/**
 * Created by twcgilbert on 02/10/2017.
 */
class DataRepositoryFakeImpl : DataRepository {
    object companion {
        const val userId1 = 1
        @JvmStatic
        val userId1Username = "Username1"
        @JvmStatic
        val userId1Email = "test1@test.com"

        const val userId2 = 2
        @JvmStatic
        val userId2Username = "Username2"
        @JvmStatic
        val userId2Email = "test2@test.com"

        const val postId1 = 1
        @JvmStatic
        val post1Title = "Hi1"
        @JvmStatic
        val post1Body = "Body1"

        const val postId2 = 2
        @JvmStatic
        val post2Title = "Hi2"
        @JvmStatic
        val post2Body = "Body2"

        @JvmStatic
        val testPost1 = Post(companion.userId1,
                companion.postId1,
                companion.post1Title,
                companion.post1Body,
                companion.userId1Username,
                companion.userId1Email)

        @JvmStatic
        val testPost2 = Post(companion.userId2,
                companion.postId2,
                companion.post2Title,
                companion.post2Body,
                companion.userId2Username,
                companion.userId2Email)
    }

    override fun getPosts(): Observable<List<Post>> {
        val posts = ArrayList<Post>()
        posts.add(companion.testPost1)
        posts.add(companion.testPost2)
        posts.add(Post(companion.userId1, 3, "Hi3", "Body", "Username", "test@test.com"))
        posts.add(Post(companion.userId1, 4, "Hi4", "Body", "Username", "test@test.com"))
        posts.add(Post(companion.userId1, 5, "Hi5", "Body", "Username", "test@test.com"))
        posts.add(Post(companion.userId1, 6, "Hi6", "Body", "Username", "test@test.com"))
        posts.add(Post(companion.userId1, 7, "Hi7", "Body", "Username", "test@test.com"))
        posts.add(Post(companion.userId1, 8, "Hi8", "Body", "Username", "test@test.com"))
        posts.add(Post(companion.userId2, 9, "Hi9", "Body", "Username", "test@test.com"))
        posts.add(Post(companion.userId2, 10, "Hi10", "Body", "Username", "test@test.com"))
        posts.add(Post(companion.userId2, 11, "Hi11", "Body", "Username", "test@test.com"))
        posts.add(Post(companion.userId2, 12, "Hi12", "Body", "Username", "test@test.com"))
        posts.add(Post(companion.userId2, 13, "Hi13", "Body", "Username", "test@test.com"))
        posts.add(Post(companion.userId2, 14, "Hi14", "Body", "Username", "test@test.com"))
        posts.add(Post(companion.userId2, 15, "Hi15", "Body", "Username", "test@test.com"))
        posts.add(Post(companion.userId2, 16, "Hi16", "Body", "Username", "test@test.com"))
        return Observable.just(posts)
    }

    override fun getUsers(): Observable<List<User>> {
        val company = Company("Name", "Cool!", "Oh well")
        val users = ArrayList<User>()
        users.add(User(
                companion.userId1, "Tom", companion.userId1Username, companion.userId1Email,
                Address("Street1", "Suit1", "City1", "94110", LatLng(0.0, 0.0)),
                "123", "http://example.com",
                company))
        users.add(User(
                companion.userId2, "Tim", companion.userId2Username, companion.userId2Email,
                Address("Street2", "Suite2", "City2", "94122", LatLng(0.0, 0.0)),
                "123", "http://example.com",
                company))
        return Observable.just(users)
    }

    override fun getComments(postId: Int): Observable<List<Comment>> {
        val comments = ArrayList<Comment>()
        comments.add(Comment(companion.postId1, 1, "Comment1", "fred@fred.com", "Body1"))
        return Observable.just(comments.filter { it.postId == postId })
    }
}