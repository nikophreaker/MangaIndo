package com.nikx.mangaindo.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChapterRead(
        @SerializedName("chapter_endpoint")
        val chapter_endpoint: String?,
        @SerializedName("chapter_pages")
        val chapter_pages: String?,
        @SerializedName("chapter_image")
        var chapter_image: List<ChapterImage>
) : Parcelable