package com.example.foddapp.NaverApi

import java.io.Serializable

data class Restaurants( // 네이버에서 검색한 식당에 관한 정보를 담을 객체
    val title: List<String>,
    val link: List<String>,
    val image: List<String>
): Serializable
