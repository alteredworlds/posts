package com.example.twcgilbert.postsapp.repo.di

import com.example.twcgilbert.postsapp.repo.Constants
import com.example.twcgilbert.postsapp.repo.DataRepository
import com.example.twcgilbert.postsapp.repo.DataRepositoryImpl
import com.example.twcgilbert.postsapp.repo.network.PostsService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


/**
 * Provides repo related items
 */
@Module
class RepoModule {

    @Provides
    @Singleton
    fun provideDataRepository(): DataRepository {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return DataRepositoryImpl(retrofit.create<PostsService>(PostsService::class.java))
    }
}