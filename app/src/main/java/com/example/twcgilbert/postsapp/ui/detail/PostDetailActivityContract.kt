package com.example.twcgilbert.postsapp.ui.detail

import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.example.twcgilbert.postsapp.common.ui.BaseActivityContract
import com.example.twcgilbert.postsapp.common.ui.BaseViewModelContract

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface PostDetailActivityContract {

    interface View  : BaseActivityContract.View {

    }

    interface ViewModel : BaseViewModelContract.ViewModel {

        val postTitle: ObservableField<String>

        val postBody: ObservableField<String>

        val postUserName: ObservableField<String>

        val postNumberOfComments: ObservableField<String>

        val userAvatarUrl: ObservableField<String>
    }
}