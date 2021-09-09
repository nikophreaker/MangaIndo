package com.nikx.mangaindo.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChapterImage(
        @SerializedName("chapter_image_link")
        val chapter_image_link: String?,
        @SerializedName("image_number")
        val image_number: String?
) : Parcelable