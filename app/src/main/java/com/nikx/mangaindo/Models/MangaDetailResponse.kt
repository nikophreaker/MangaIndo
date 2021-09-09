package com.nikx.mangaindo.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MangaDetailResponse(
        @SerializedName("title")
        val title: String?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("author")
        val author: String?,
        @SerializedName("status")
        val status: String?,
        @SerializedName("thumb")
        val thumb: String?,
        @SerializedName("genre_list")
        val genre_list: List<Genres>,
        @SerializedName("chapter")
        var chapter_list: List<MangaChapter>
) : Parcelable