package com.example.twcgilbert.postsapp.io.data

import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize

/**
 * Created by twcgilbert on 01/10/2017.
 */
data class User(
        val id: Int,
        val name: String,
        val username: String,
        val email: String,
        val address: Address,
        val phone: String,
        val website: String,
        val company: Company
)