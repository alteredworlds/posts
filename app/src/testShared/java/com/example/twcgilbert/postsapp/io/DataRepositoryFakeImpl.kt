package com.example.twcgilbert.postsapp.io

import com.example.twcgilbert.postsapp.io.data.*
import io.reactivex.Observable

/**
 * Created by twcgilbert on 02/10/2017.
 */
class DataRepositoryFakeImpl : DataRepository {
    override fun getPosts(): Observable<List<Post>> {
        val posts = ArrayList<Post>()
        posts.add(Post(1, 1, "Hi1", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 2, "Hi2", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 3, "Hi3", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 4, "Hi4", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 5, "Hi5", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 6, "Hi6", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 7, "Hi7", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 8, "Hi8", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 9, "Hi9", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 10, "Hi10", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 11, "Hi11", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 12, "Hi12", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 13, "Hi13", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 14, "Hi14", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 15, "Hi15", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 16, "Hi16", "Body", "Username", "test@test.com"))
        return Observable.just(posts)
    }

    override fun getUsers(): Observable<List<User>> {
        val users = ArrayList<User>()
        users.add(User(
                1, "Tom", "Username", "test@test.com",
                Address("Street", "City", "City", "Zip", LatLng(0.0, 0.0)),
                "123", "http://example.com",
                Company("Name", "Cool!", "Oh well")))
        return Observable.just(users)
    }

    override fun getComments(postId: Int): Observable<List<Comment>> {
        return Observable.just(ArrayList<Comment>())
    }
}