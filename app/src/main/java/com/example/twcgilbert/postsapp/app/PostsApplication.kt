package com.example.twcgilbert.postsapp.app

import android.app.Activity
import android.app.Application
import com.example.twcgilbert.postsapp.BuildConfig
import com.example.twcgilbert.postsapp.app.di.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by twcgilbert on 01/10/2017.
 */
open class PostsApplication : Application(), HasActivityInjector {
    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        // memory leak detection library (NOP'd out of release builds)
        LeakCanary.install(this);
        injectDependencies()
        setupLogger()
    }

    protected open fun injectDependencies() {
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    private fun setupLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun activityInjector() = dispatchingActivityInjector
}