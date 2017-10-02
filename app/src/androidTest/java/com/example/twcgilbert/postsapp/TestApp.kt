package com.example.twcgilbert.postsapp

import com.example.twcgilbert.postsapp.common.di.DaggerTestAppComponent

/**
 * Created by twcgilbert on 02/10/2017.
 */
class TestApp : PostsApplication() {
    override fun injectDependencies() {
        // can be used to supply alternate modules for testing eg: DataRepository
        DaggerTestAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }
}