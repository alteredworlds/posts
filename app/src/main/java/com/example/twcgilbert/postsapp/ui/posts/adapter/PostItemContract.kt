package com.example.twcgilbert.postsapp.ui.posts.adapter

import android.view.View

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface PostItemContract : View.OnClickListener {

    val title: String?

    val userAvatarUrl: String?
}