package com.nikx.mangaindo.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    var BASE_URL =  "https://mangamint.kaedenoki.net/api/"
    //var BASE_URL =  "https://my-json-server.typicode.com/typicode/demo/"
    fun getClient(): Api {
          val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
        return retrofit.create(Api::class.java)
    }
}