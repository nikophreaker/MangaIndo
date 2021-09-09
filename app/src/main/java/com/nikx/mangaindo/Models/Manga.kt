package com.nikx.mangaindo.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Manga(
        @SerializedName("title")
        val title: String?,
        @SerializedName("thumb")
        val thumb: String?,
        @SerializedName("endpoint")
        val endpoint: String?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("updated_on")
        val updated_on: String?,
        @SerializedName("chapter")
        val chapter: String?
) : Parcelable