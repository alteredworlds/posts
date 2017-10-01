package com.example.twcgilbert.postsapp.ui.posts

import android.content.Context
import android.databinding.ObservableBoolean
import com.example.twcgilbert.postsapp.ui.posts.adapter.PostsAdapter
import android.support.v7.widget.RecyclerView
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

    interface ViewModel : BaseViewModelContract.ViewModel {

        val adapter: PostsAdapter

        val layoutManager: RecyclerView.LayoutManager

        val progressVisible: ObservableBoolean
    }
}