package com.example.twcgilbert.postsapp.common.ui

import android.content.Context

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface BaseActivityContract {

    interface View {

        val context: Context

        fun showError(message: String)
    }
}