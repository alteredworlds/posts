package com.example.twcgilbert.postsapp.ui.posts

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.example.twcgilbert.postsapp.common.ui.BaseActivityContract
import com.example.twcgilbert.postsapp.common.ui.BaseViewModelContract
import com.example.twcgilbert.postsapp.io.data.Post

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface PostsActivityContract {

    interface PostClicked {

        fun onPostClicked(post: Post)
    }

    interface View : BaseActivityContract.View {

        fun navigateForPost(post: Post)
    }

    interface ViewModel : BaseViewModelContract.ViewModel, PostsActivityContract.PostClicked {

        val posts: ObservableField<List<Post>>

        val progressVisible: ObservableBoolean
    }
}