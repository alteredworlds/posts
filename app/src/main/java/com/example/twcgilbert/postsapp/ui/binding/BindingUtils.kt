package com.example.twcgilbert.postsapp.ui.binding

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by twcgilbert on 01/10/2017.
 */
@BindingAdapter("horizontalDivider")
fun setVerticalDivider(recyclerView: RecyclerView, drawable: Drawable) {
    val verticalDecoration = DividerItemDecoration(
            recyclerView.context,
            DividerItemDecoration.VERTICAL)
    verticalDecoration.setDrawable(drawable)
    recyclerView.addItemDecoration(verticalDecoration)
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Picasso.with(imageView.context)
            .load(url)
            .into(imageView);
}

@BindingAdapter("snackBarText")
fun setSnackbarText(coordinatorLayout: CoordinatorLayout, text: String?) {
    if ((null != text) && text.isNotEmpty()) {
        Snackbar.make(coordinatorLayout, text, Snackbar.LENGTH_LONG)
                .show();
    }
}