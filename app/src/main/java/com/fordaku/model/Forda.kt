package com.fordaku.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Forda(
    val strName: String = "",
    val strLocation: String = "",
    val strDescription: String = "",
    val strEmail: String = "",
    val intCreatedAt: Int = 0,
    val userId: String = "",
):Parcelable
