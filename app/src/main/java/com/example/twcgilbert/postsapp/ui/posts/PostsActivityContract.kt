package com.example.twcgilbert.postsapp.ui.posts

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.example.twcgilbert.postsapp.common.ui.BaseContract
import com.example.twcgilbert.postsapp.repo.data.Post

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface PostsActivityContract {

    interface PostClicked {

        fun onPostClicked(post: Post)
    }

    interface View : BaseContract.View {

        fun navigateForPost(post: Post)
    }

    interface ViewModel : BaseContract.ViewModel, PostClicked {

        val posts: ObservableField<List<Post>>

        val progressVisible: ObservableBoolean
    }
}