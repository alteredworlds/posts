package com.example.twcgilbert.postsapp.ui.detail

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.example.twcgilbert.postsapp.common.ui.BaseContract

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface PostDetailActivityContract {

    interface ViewModel : BaseContract.ViewModel {

        val error: ObservableField<String>

        val postTitle: ObservableField<String>

        val postBody: ObservableField<String>

        val postUserName: ObservableField<String>

        val postNumberOfComments: ObservableInt

        val userAvatarUrl: ObservableField<String>
    }
}