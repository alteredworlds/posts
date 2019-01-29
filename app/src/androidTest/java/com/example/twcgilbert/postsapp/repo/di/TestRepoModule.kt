package com.example.twcgilbert.postsapp.repo.di

import android.app.Application
import androidx.room.Room
import com.example.twcgilbert.postsapp.repo.DataRepository
import com.example.twcgilbert.postsapp.repo.DataRepositoryImpl
import com.example.twcgilbert.postsapp.repo.domain.*
import com.example.twcgilbert.postsapp.repo.network.PostsService
import com.example.twcgilbert.postsapp.repo.network.PostsServiceFake
import com.example.twcgilbert.postsapp.repo.persistence.LocalDatabase
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
    fun providesLocalDatabase(context: Application): LocalDatabase =
    // using an in-memory database because the information stored here disappears after test
            Room.inMemoryDatabaseBuilder(context,
                    LocalDatabase::class.java)
                    // allowing main thread queries, just for testing
                    .allowMainThreadQueries()
                    .build()

    @Provides
    @Singleton
    fun provideDataRepository(
            localDatabase: LocalDatabase
    ): DataRepository = DataRepositoryImpl(localDatabase)

    @Provides
    @Singleton
    fun providesPostsService(): PostsService = PostsServiceFake()

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