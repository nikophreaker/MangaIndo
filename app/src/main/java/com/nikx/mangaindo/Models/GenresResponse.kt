package com.nikx.mangaindo.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenresResponse(
        @SerializedName("status")
        val status: String?,
        @SerializedName("message")
        val message: String?,
        @SerializedName("list_genre")
        val list_genre: MutableList<Genres>
) : Parcelable