package com.nikx.mangaindo.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genres(
        @SerializedName("genre_name")
        val genre_name: String?
) : Parcelable