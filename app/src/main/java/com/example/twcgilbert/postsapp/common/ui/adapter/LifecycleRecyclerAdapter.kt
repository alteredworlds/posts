package com.example.twcgilbert.postsapp.common.ui.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class LifecycleRecyclerAdapter<T : LifecycleViewHolder> : RecyclerView.Adapter<T>() {

    override fun onViewAttachedToWindow(holder: T) {
        super.onViewAttachedToWindow(holder)
        holder.onAppear()
    }

    override fun onViewDetachedFromWindow(holder: T) {
        super.onViewDetachedFromWindow(holder)
        holder.onDisappear()
    }

}