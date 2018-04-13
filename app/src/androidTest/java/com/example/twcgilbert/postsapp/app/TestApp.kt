package com.example.twcgilbert.postsapp.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.twcgilbert.postsapp.app.di.DaggerTestAppComponent


/**
 * Created by twcgilbert on 02/10/2017.
 */
class TestApp : PostsApplication(), Application.ActivityLifecycleCallbacks {

    var currentActivity: Activity? = null

    override fun injectDependencies() {
        // can be used to supply alternate modules for testing eg: DataRepository
        DaggerTestAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this);
    }

    override fun onActivityResumed(activity: Activity?) {
        currentActivity = activity;
    }

    override fun onActivityStarted(activity: Activity?) {
        currentActivity = activity;
    }

    override fun onActivityCreated(activity: Activity?, p1: Bundle?) {
        currentActivity = activity;
    }

    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, p1: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }
}