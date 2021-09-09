package com.nikx.mangaindo.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MangaChapter(
        @SerializedName("chapter_title")
        val chapter_title: String?,
        @SerializedName("chapter_endpoint")
        val chapter_endpoint: String?
) : Parcelable