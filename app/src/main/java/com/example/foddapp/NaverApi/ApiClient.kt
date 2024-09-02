package com.example.foddapp.NaverApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://openapi.naver.com"

    fun create(): NaverApiService { // 식당의 링크와 이름을 가져오는 함수
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(NaverApiService::class.java)
    }
    fun ImageCreate(): NaverApiServiceImage {// 식당의 이미지를 가져오는 함수
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(NaverApiServiceImage::class.java)
    }
}
