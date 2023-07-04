package com.example.warrenlogin.other

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.example.warrenlogin.feature_login.domain.util.Resource
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


fun ImageView.loadFromPicasso(
    imageUrl: String,
    @DrawableRes errorPlaceholder: Int,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {
    Picasso.get()
        .load(imageUrl)
        .error(errorPlaceholder)
        .into(this, object : Callback {
            override fun onSuccess() {
                onSuccess()
            }

            override fun onError(e: Exception?) {
                onError()
            }
        })
}