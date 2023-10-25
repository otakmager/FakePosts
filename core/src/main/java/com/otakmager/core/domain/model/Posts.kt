package com.otakmager.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Posts(
    val id: Int,
    val title: String,
    val body: String,
    val isFavorite: Boolean
) : Parcelable