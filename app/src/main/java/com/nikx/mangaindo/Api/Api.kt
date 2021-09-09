package com.nikx.mangaindo.Api

import com.nikx.mangaindo.Models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {
    //manga/page/1
    @GET("manga/page/{page}")
   //fun manga_list(): Call<List<Manga>>
    fun manga_list(@Path("page") page: Int?): Call<MyResponse>
    
    @GET("manga/detail/{endpoint}")
    fun getDetail(@Path("endpoint") manga: String?): Call<MangaDetailResponse>

    @GET("chapter/{endpoint}")
    fun getChapter(@Path("endpoint") ch: String?): Call<ChapterRead>

    @GET("search/{endpoint}")
    fun getSearch(@Path("endpoint") search: String?): Call<MyResponse>

    @GET("genres")
    fun getGenre(): Call<GenresResponse>

    @GET("genres/{endpoint}/{page}")
    fun getGenres(@Path("endpoint") genre: String?,@Path("page") page: Int?): Call<MyResponse>
}