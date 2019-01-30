package com.example.twcgilbert.postsapp.common.ui.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

open class SimpleEvent : Event<Unit>(Unit)

fun LiveData<SimpleEvent>.observeEvent(
        owner: LifecycleOwner,
        onEventUnhandledContent: () -> Unit
) {
    observe(owner, Observer { it?.getContentIfNotHandled()?.let { onEventUnhandledContent } })
}