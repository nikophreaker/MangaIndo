package com.nikx.mangaindo.Models

import com.google.gson.annotations.SerializedName

class MyResponse {
    @SerializedName("status")
    var status: String? = null
    @SerializedName("manga_list")
    lateinit var manga_list: MutableList<Manga>
    @SerializedName("message")
    var message: String? = null
}