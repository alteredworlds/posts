package com.example.twcgilbert.postsapp.io

import com.example.twcgilbert.postsapp.io.data.*
import io.reactivex.Observable

/**
 * Created by twcgilbert on 02/10/2017.
 */
class DataRepositoryFakeImpl : DataRepository {
    override fun getPosts(): Observable<List<Post>> {
        val posts = ArrayList<Post>()
        posts.add(Post(1, 1, "Hi", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 2, "Hi", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 3, "Hi", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 4, "Hi", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 5, "Hi", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 6, "Hi", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 7, "Hi", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 8, "Hi", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 9, "Hi", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 10, "Hi", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 11, "Hi", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 12, "Hi", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 13, "Hi", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 14, "Hi", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 15, "Hi", "Body", "Username", "test@test.com"))
        posts.add(Post(1, 16, "Hi", "Body", "Username", "test@test.com"))
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