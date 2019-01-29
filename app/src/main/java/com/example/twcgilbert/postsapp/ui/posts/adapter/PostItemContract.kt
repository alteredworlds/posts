package com.example.twcgilbert.postsapp.ui.posts.adapter

import android.view.View
import androidx.databinding.ObservableField

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface PostItemContract : View.OnClickListener {

    val title: ObservableField<String>

    val userAvatarUrl: ObservableField<String>
}