package com.example.twcgilbert.postsapp.common.ui

import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by twcgilbert on 01/10/2017.
 */
open class BaseActivity : DaggerAppCompatActivity(), BaseActivityContract.View {

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}