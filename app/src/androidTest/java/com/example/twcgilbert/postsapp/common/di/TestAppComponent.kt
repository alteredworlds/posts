package com.example.twcgilbert.postsapp.common.di

import android.app.Application
import com.example.twcgilbert.postsapp.TestApp
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
        AppModule::class,
        ActivityBuilderModule::class)
)
interface TestAppComponent {
    fun inject(application: TestApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestAppComponent
    }
}