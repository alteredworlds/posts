package com.example.twcgilbert.postsapp

import com.example.twcgilbert.postsapp.common.di.DaggerAppComponent

/**
 * Created by twcgilbert on 02/10/2017.
 */
class TestApp : PostsApplication() {
    override fun injectDependencies() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }
}