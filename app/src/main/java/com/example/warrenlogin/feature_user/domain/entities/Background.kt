package com.example.warrenlogin.feature_user.domain.entities

import com.example.warrenlogin.feature_user.data.database.BackgroundDb

data class Background(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
) {
    fun backgroundDomainToDb() : BackgroundDb {
        return BackgroundDb(
            full = full,
            raw = raw,
            regular = regular,
            small = small,
            thumb = thumb
        )
    }
}
