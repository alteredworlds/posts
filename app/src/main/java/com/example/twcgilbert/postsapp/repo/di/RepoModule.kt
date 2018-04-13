package com.example.twcgilbert.postsapp.repo.di

import android.app.Application
import com.example.twcgilbert.postsapp.repo.Constants
import com.example.twcgilbert.postsapp.repo.DataRepository
import com.example.twcgilbert.postsapp.repo.DataRepositoryImpl
import com.example.twcgilbert.postsapp.repo.network.NetworkMonitorInterceptor
import com.example.twcgilbert.postsapp.repo.network.PostsService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton


/**
 * Provides repo related items
 */
@Module
class RepoModule {

    @Provides
    @Singleton
    fun providesNetworkMonitorInterceptor(context: Application): NetworkMonitorInterceptor =
            NetworkMonitorInterceptor(context)

    @Provides
    @Singleton
    fun provideDataRepository(networkMonitorInterceptor: NetworkMonitorInterceptor): DataRepository {
        // helps with debugging - we get to see HTTP body in logcat for debug builds
        val loggingInterceptor = HttpLoggingInterceptor(
                HttpLoggingInterceptor.Logger { message ->
                    Timber.tag("OkHttp").d(message)
                }).setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(networkMonitorInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()

        return DataRepositoryImpl(retrofit.create<PostsService>(PostsService::class.java))
    }
}