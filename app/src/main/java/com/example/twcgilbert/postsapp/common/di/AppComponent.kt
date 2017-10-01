package com.example.twcgilbert.postsapp.common.di

import android.app.Application
import com.example.twcgilbert.postsapp.PostsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by twcgilbert on 01/10/2017.
 */
@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class)
)
interface AppComponent {
    fun inject(application: PostsApplication)

    @Component.Builder interface Builder {
        @BindsInstance fun application(application: Application): Builder

        fun build(): AppComponent
    }
}