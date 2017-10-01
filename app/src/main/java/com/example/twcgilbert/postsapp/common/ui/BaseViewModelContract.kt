package com.example.twcgilbert.postsapp.common.ui

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface BaseViewModelContract {

    interface ViewModel {

        fun onCreate()

        fun onDestroy()
    }
}