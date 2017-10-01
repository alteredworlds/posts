package com.example.twcgilbert.postsapp.common.di

import com.example.twcgilbert.postsapp.io.Constants
import com.example.twcgilbert.postsapp.io.DataRepository
import com.example.twcgilbert.postsapp.io.DataRepositoryImpl
import com.example.twcgilbert.postsapp.io.data.User
import com.example.twcgilbert.postsapp.io.network.PostsService
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


/**
 * The Dagger module of the application, containing all application-wide singletons.
 */
@Module
class AppModule {

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