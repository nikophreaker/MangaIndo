package com.nikx.mangaindo.Models

data class MangaResponse (var page : Int,
                          val results : ArrayList<Manga>,
                          val totalResult : Int,
                          val totalPage : Int)