package com.example.tmdp.common

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.tmdp.AppInstance
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

object GlideUtils {

    fun showImage(url: String?, placeHolder: Int, imageView: ImageView) {
        Glide.with(AppInstance.getInstance())
            .load(url)
            .thumbnail(0.5f)
            .apply(
                RequestOptions()
                    .placeholder(placeHolder).format(DecodeFormat.PREFER_RGB_565).encodeFormat(
                        Bitmap.CompressFormat.PNG
                    ).priority(Priority.HIGH)
            )
            .transition(DrawableTransitionOptions.withCrossFade(300))
            .into(imageView)
    }

    fun showBlurImage(url: String, placeHolder: Int, imageView: ImageView, blurRadius: Int) {
        val multi = MultiTransformation<Bitmap>(
            BlurTransformation(blurRadius),
            RoundedCornersTransformation(0, 0, RoundedCornersTransformation.CornerType.BOTTOM)
        )
        Glide.with(AppInstance.getInstance())
            .load(url)
            .thumbnail(0.5f)
            .apply(RequestOptions.bitmapTransform(multi))
            .apply(
                RequestOptions()
                    .placeholder(placeHolder).format(DecodeFormat.PREFER_RGB_565).encodeFormat(
                        Bitmap.CompressFormat.PNG
                    ).priority(Priority.HIGH)
            )
            .transition(DrawableTransitionOptions.withCrossFade(300))
            .into(imageView)
    }

    fun showImageWithLoading(
        url: String,
        placeHolder: Int,
        imageView: ImageView,
        progressBar: ProgressBar
    ) {

        val options =
            RequestOptions().placeholder(placeHolder).format(DecodeFormat.PREFER_ARGB_8888)
                .encodeFormat(Bitmap.CompressFormat.PNG)
                .priority(Priority.HIGH)

        Glide.with(AppInstance.getInstance())
            .load(url)
            .thumbnail(0.5f)
            .apply(options)
            .transition(DrawableTransitionOptions.withCrossFade(300))
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean { //                        progressBar.setVisibility(View.GONE);
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }
            })
            .into(imageView)
    }
}