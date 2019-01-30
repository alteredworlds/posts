package com.example.twcgilbert.postsapp.ui.detail

import androidx.lifecycle.LiveData

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface PostDetailActivityContract {

    interface ViewModel {

        val postTitle: LiveData<String>

        val postBody: LiveData<String>

        val postUserName: LiveData<String>

        val userAvatarUrl: LiveData<String>

        val postNumberOfComments: LiveData<Int>

        val error: LiveData<String>
    }
}