package com.example.twcgilbert.postsapp.app.di

import android.app.Application
import com.example.twcgilbert.postsapp.app.TestApp
import com.example.twcgilbert.postsapp.repo.di.TestRepoModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by twcgilbert on 02/10/2017.
 */
@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        //RepoModule::class, // uncomment => all posts in androidTest
        TestRepoModule::class, // this shows just one post, so one or the other
        ActivityBuilderModule::class)
)
interface TestAppComponent {
    fun inject(application: TestApp): TestApp

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestAppComponent
    }
}