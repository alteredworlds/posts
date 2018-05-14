package com.example.twcgilbert.postsapp.repo.domain

import com.example.twcgilbert.postsapp.repo.network.PostsService
import com.example.twcgilbert.postsapp.repo.persistence.LocalDatabase
import io.reactivex.schedulers.Schedulers

class RefreshCommentsUseCaseImpl(
        private val api: PostsService,
        private val mapper: Mapper,
        private val db: LocalDatabase
) : RefreshCommentsUseCase {

    override fun execute(postId: Int) = api
            .getComments(postId)
            .subscribeOn(Schedulers.io())
            .map {
                mapper.mapCommentDtos(it)
            }
            .map {
                db.commentDao().replaceAll(it)
            }
            .toCompletable()
}