package com.example.twcgilbert.postsapp.ui.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

/**
 * Created by twcgilbert on 01/10/2017.
 */
@BindingAdapter("horizontalDivider")
fun setVerticalDivider(recyclerView: androidx.recyclerview.widget.RecyclerView, drawable: Drawable) {
    val verticalDecoration = androidx.recyclerview.widget.DividerItemDecoration(
            recyclerView.context,
            androidx.recyclerview.widget.DividerItemDecoration.VERTICAL)
    verticalDecoration.setDrawable(drawable)
    recyclerView.addItemDecoration(verticalDecoration)
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    if ((url == null) || url.isEmpty()) {
        imageView.setImageDrawable(null)
    } else {
        Picasso.get()
                .load(url)
                .into(imageView);
    }
}

@BindingAdapter("snackBarText")
fun setSnackbarText(coordinatorLayout: androidx.coordinatorlayout.widget.CoordinatorLayout, text: String?) {
    if ((null != text) && text.isNotEmpty()) {
        Snackbar.make(coordinatorLayout, text, Snackbar.LENGTH_LONG)
                .show();
    }
}