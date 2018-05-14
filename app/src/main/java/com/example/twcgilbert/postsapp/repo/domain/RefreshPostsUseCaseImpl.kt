package com.example.twcgilbert.postsapp.repo.domain

import com.example.twcgilbert.postsapp.repo.network.PostsService
import com.example.twcgilbert.postsapp.repo.network.model.PostDto
import com.example.twcgilbert.postsapp.repo.network.model.UserDto
import com.example.twcgilbert.postsapp.repo.persistence.LocalDatabase
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class RefreshPostsUseCaseImpl(
        private val api: PostsService,
        private val mapper: Mapper,
        private val db: LocalDatabase) :
        RefreshPostsUseCase {

    override fun execute() = api
            .getPosts()
            .subscribeOn(Schedulers.io())
            .zipWith(
                    api.getUsers().subscribeOn(Schedulers.io()),
                    BiFunction<List<PostDto>, List<UserDto>, Pair<List<UserDto>, List<PostDto>>> { posts, users ->
                        Pair(users, posts)
                    })
            .map {
                Pair(mapper.mapUserDtos(it.first), mapper.mapPostDtos(it.second))
            }
            .map {
                db.userDao().replaceAll(it.first)
                db.postDao().replaceAll(it.second)
            }
            .toCompletable()
}