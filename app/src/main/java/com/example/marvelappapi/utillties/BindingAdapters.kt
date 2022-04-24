package com.example.marvelappapi.utillties

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
//fun loadImage(view: imageView, imageUrl: String) {
fun loadImage(view: ImageView, photo: String) {
    Glide.with(view.context)
        .load(photo)
//        .load(imageUrl)
        .into(view)
}