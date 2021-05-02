package com.tutorials.countries.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.tutorials.countries.R

fun getProgressDrawable(context: Context) = CircularProgressDrawable(context)
    .apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }

fun ImageView.loadImage(url: String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.mipmap.ic_launcher_round)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}