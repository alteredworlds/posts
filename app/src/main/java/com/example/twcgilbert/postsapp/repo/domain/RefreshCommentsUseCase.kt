package com.example.twcgilbert.postsapp.repo.domain

import io.reactivex.Completable

interface RefreshCommentsUseCase {

    fun execute(postId: Int): Completable
}