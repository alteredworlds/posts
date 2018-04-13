package com.example.twcgilbert.postsapp.repo

import com.example.twcgilbert.postsapp.repo.data.*
import com.example.twcgilbert.postsapp.repo.network.PostsService
import io.reactivex.Observable

/**
 * Created by twcgilbert on 23/10/2017.
 */
open class PostsServiceFake : PostsService {
    object companion {
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
        val posts = ArrayList<SimplePost>()
        posts.add(companion.testSimplePost1)
        posts.add(companion.testSimplePost2)
        posts.add(SimplePost(companion.userId1, 3, "Hi3", "Body"))
        posts.add(SimplePost(companion.userId1, 4, "Hi4", "Body"))
        posts.add(SimplePost(companion.userId1, 5, "Hi5", "Body"))
        posts.add(SimplePost(companion.userId1, 6, "Hi6", "Body"))
        posts.add(SimplePost(companion.userId1, 7, "Hi7", "Body"))
        posts.add(SimplePost(companion.userId1, 8, "Hi8", "Body"))
        posts.add(SimplePost(companion.userId2, 9, "Hi9", "Body"))
        posts.add(SimplePost(companion.userId2, 10, "Hi10", "Body"))
        posts.add(SimplePost(companion.userId2, 11, "Hi11", "Body"))
        posts.add(SimplePost(companion.userId2, 12, "Hi12", "Body"))
        posts.add(SimplePost(companion.userId2, 13, "Hi13", "Body"))
        posts.add(SimplePost(companion.userId2, 14, "Hi14", "Body"))
        posts.add(SimplePost(companion.userId2, 15, "Hi15", "Body"))
        posts.add(SimplePost(companion.userId2, 16, "Hi16", "Body"))
        return Observable.just(posts)
    }

    override fun getUsers(): Observable<List<User>> {
        val users = ArrayList<User>()
        users.add(User(
                companion.userId1, "Tom", companion.userId1Username, companion.userId1Email))
        users.add(User(
                companion.userId2, "Tim", companion.userId2Username, companion.userId2Email))
        return Observable.just(users)
    }

    override fun getComments(postId: Int): Observable<List<Comment>> {
        val comments = ArrayList<Comment>()
        comments.add(Comment(companion.postId1, 1, "Comment1", "fred@fred.com", "Body1"))
        return Observable.just(comments.filter { it.postId == postId })
    }
}