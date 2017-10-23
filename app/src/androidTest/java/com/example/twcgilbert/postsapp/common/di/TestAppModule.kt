package com.example.twcgilbert.postsapp.common.di

import com.example.twcgilbert.postsapp.io.DataRepository
import com.example.twcgilbert.postsapp.io.DataRepositoryImpl
import com.example.twcgilbert.postsapp.io.PostsServiceFake
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by twcgilbert on 02/10/2017.
 */

@Module
class TestAppModule {
    @Provides
    @Singleton
    fun provideDataRepository(): DataRepository {
        return DataRepositoryImpl(PostsServiceFake())
    }
}