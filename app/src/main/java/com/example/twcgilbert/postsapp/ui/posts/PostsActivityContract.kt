package com.example.twcgilbert.postsapp.ui.posts

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.paging.PagedList
import com.example.twcgilbert.postsapp.common.ui.BaseContract
import com.example.twcgilbert.postsapp.repo.model.Post

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface PostsActivityContract {

    interface PostClicked {

        fun onPostClicked(post: Post)
    }

    interface NavigateForPost {

        fun navigateForPost(post: Post)
    }

    interface ViewModel : BaseContract.ViewModel, PostClicked {

        val error: ObservableField<String>

        val posts: ObservableField<PagedList<Post>>

        val progressVisible: ObservableBoolean

        fun onRefresh()
    }
}