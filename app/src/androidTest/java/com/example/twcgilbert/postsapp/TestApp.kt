package com.example.twcgilbert.postsapp

import android.app.Activity
import android.app.Application
import android.os.Bundle

//TODO: uncomment this to use test data source.
//import com.example.twcgilbert.postsapp.common.di.DaggerTestAppComponent

/**
 * Created by twcgilbert on 02/10/2017.
 */
class TestApp : PostsApplication(), Application.ActivityLifecycleCallbacks {

    var currentActivity: Activity? = null

    //TODO: uncomment this to use test data source. NOTE: Android Studio *may* have an issue
    //TODO: with Dagger compiler in androidTests; for me this only works after one
    //TODO: successful androidTest run *without* this uncommented
//    override fun injectDependencies() {
//        // can be used to supply alternate modules for testing eg: DataRepository
//        DaggerTestAppComponent.builder()
//                .application(this)
//                .build()
//                .inject(this)
//    }

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