package com.example.warrenlogin.feature_user.data.response

import com.example.warrenlogin.feature_user.data.database.BackgroundDb
import com.google.gson.annotations.SerializedName

data class BackgroundResponse(
    @SerializedName("full")
    val full: String,
    @SerializedName("raw")
    val raw: String,
    @SerializedName("regular")
    val regular: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("thumb")
    val thumb: String
) {
    fun toBackGroundDb() : BackgroundDb {
        return BackgroundDb(
            full = full,
            raw = raw,
            regular = regular,
            small = small,
            thumb = thumb
        )
    }
}