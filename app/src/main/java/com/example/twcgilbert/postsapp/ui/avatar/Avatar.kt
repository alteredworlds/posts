package com.example.twcgilbert.postsapp.ui.avatar

import com.example.twcgilbert.postsapp.repo.model.Post

const val ADORABLE_URL = "https://api.adorable.io/avatars/128/"
const val ADORABLE_IMAGE_EXTENSION = ".png"

val Post.imageUrl: String
    get() = ADORABLE_URL + userEmail + ADORABLE_IMAGE_EXTENSION