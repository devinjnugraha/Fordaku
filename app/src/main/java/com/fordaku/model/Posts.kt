package com.fordaku.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Posts(
    val strTitle: String = "",
    val strContent: String = "",
    val intCreatedAt: Int = 0,
    val userId: String = "",
):Parcelable
