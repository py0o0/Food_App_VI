package com.example.foddapp.commu

import android.util.Log

//코루틴을 사용한 비동기 함수
class GetFood {
    suspend fun FoodData(cate1: String, cate2: String): Food {
        val apiService = RetrofitComu.client.create(ComuApi::class.java) //RetrofitComu의 create 함수를 사용하여 ComuApi 인터페이스를 구현
        return try {
            apiService.getFoodsRandomByCategory(cate1, cate2) // 서버에서 받아온 객체 반환
        } catch (e: Exception) {
            Log.e("GetFood", "API call failed", e)
            Food(food = "Unknown", cate1 = cate1, cate2 = cate2)// 오류 방지를 위한 기본값을 갖는 Food 객체 반환
        }
    }

}