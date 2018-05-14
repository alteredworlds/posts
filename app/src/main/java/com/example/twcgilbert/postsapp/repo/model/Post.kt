package com.example.twcgilbert.postsapp.repo.model

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by twcgilbert on 01/10/2017.
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Post(
        val id: Int,
        val userId: Int,
        val title: String?,
        val body: String?,
        val userName: String?,
        val userEmail: String?
) : Parcelable

fun Intent.putPost(post: Post) = putExtra(Intent.EXTRA_REFERRER, post)

fun Intent.getPost(): Post = getParcelableExtra(Intent.EXTRA_REFERRER)