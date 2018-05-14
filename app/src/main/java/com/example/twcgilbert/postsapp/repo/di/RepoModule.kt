package com.example.twcgilbert.postsapp.repo.di

import android.app.Application
import android.arch.persistence.room.Room
import com.example.twcgilbert.postsapp.repo.DataRepository
import com.example.twcgilbert.postsapp.repo.DataRepositoryImpl
import com.example.twcgilbert.postsapp.repo.domain.*
import com.example.twcgilbert.postsapp.repo.network.NetworkMonitorInterceptor
import com.example.twcgilbert.postsapp.repo.network.PostsService
import com.example.twcgilbert.postsapp.repo.persistence.LocalDatabase
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
    fun providesLocalDatabase(context: Application): LocalDatabase =
            Room.databaseBuilder(context,
                    LocalDatabase::class.java, "Postsapp.db")
                    .build()

    @Provides
    @Singleton
    fun provideDataRepository(
            localDatabase: LocalDatabase
    ): DataRepository = DataRepositoryImpl(localDatabase)

    @Provides
    @Singleton
    fun providesPostsService(
            networkMonitorInterceptor: NetworkMonitorInterceptor): PostsService {
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
                .baseUrl(PostsService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()

        return retrofit.create<PostsService>(PostsService::class.java)
    }

    @Provides
    @Singleton
    fun providesRefreshPostsUseCase(
            api: PostsService,
            mapper: Mapper,
            localDatabase: LocalDatabase
    ): RefreshPostsUseCase {
        return RefreshPostsUseCaseImpl(api, mapper, localDatabase)
    }

    @Provides
    @Singleton
    fun providesRefreshCommentsUseCase(
            api: PostsService,
            mapper: Mapper,
            localDatabase: LocalDatabase
    ): RefreshCommentsUseCase = RefreshCommentsUseCaseImpl(
            api,
            mapper,
            localDatabase)
}