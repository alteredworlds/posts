package com.example.twcgilbert.postsapp.io.data

/**
 * Created by twcgilbert on 01/10/2017.
 */
data class Address(
        val street: String,
        val suite: String,
        val city: String,
        val zipcode: String,
        val geo: LatLng
)