package com.example.twcgilbert.postsapp.common.ui.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

// Used as a wrapper for data exposed via a LiveData representing an event
open class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set // allow external read but not write

    // returns the content and prevents re-use
    fun getContentIfNotHandled(): T? =
            if (hasBeenHandled)
                null
            else {
                hasBeenHandled = true
                content
            }

    // returns content even if already handled
    fun peekContent() = content
}

inline fun <T> LiveData<Event<T>>.observeEvent(
        owner: LifecycleOwner,
        crossinline onEventUnhandledContent: (T) -> Unit
) {
    observe(owner, Observer { it?.getContentIfNotHandled()?.let(onEventUnhandledContent) })
}