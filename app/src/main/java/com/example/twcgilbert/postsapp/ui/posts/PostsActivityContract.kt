package com.example.twcgilbert.postsapp.ui.posts

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.twcgilbert.postsapp.common.ui.util.Event
import com.example.twcgilbert.postsapp.repo.model.Post

/**
 * Created by twcgilbert on 01/10/2017.
 */
interface PostsActivityContract {

    interface PostClicked {

        fun onPostClicked(post: Post)
    }

    interface ViewModel : PostClicked {

        val error: LiveData<String>

        val posts: LiveData<PagedList<Post>>

        val progressVisible: LiveData<Boolean>

        val navigateForPost: LiveData<Event<Post>>

        fun onRefresh()
    }
}