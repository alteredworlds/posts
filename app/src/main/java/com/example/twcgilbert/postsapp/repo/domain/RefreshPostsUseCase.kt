package com.example.twcgilbert.postsapp.repo.domain

import io.reactivex.Completable

interface RefreshPostsUseCase {

    fun execute(): Completable
}