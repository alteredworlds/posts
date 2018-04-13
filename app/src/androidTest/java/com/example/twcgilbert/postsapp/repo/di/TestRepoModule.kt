package com.example.twcgilbert.postsapp.repo.di

import com.example.twcgilbert.postsapp.repo.DataRepository
import com.example.twcgilbert.postsapp.repo.DataRepositoryImpl
import com.example.twcgilbert.postsapp.repo.PostsServiceFake
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by twcgilbert on 02/10/2017.
 */

@Module
class TestRepoModule {
    @Provides
    @Singleton
    fun provideDataRepository(): DataRepository {
        return DataRepositoryImpl(PostsServiceFake())
    }
}