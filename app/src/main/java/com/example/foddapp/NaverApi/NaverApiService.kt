package com.example.foddapp.NaverApi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverApiService {  // 식당 검색 인터페이스
    @GET("/v1/search/local")
    fun searchBlogs(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String,
        @Query("display") display: Int
    ): Call<SearchResponse>
}
