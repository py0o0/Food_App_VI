package com.example.foddapp.NaverApi

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.charset.StandardCharsets


class NaverApiMain {
    suspend fun NaverApi(q: String): String { // 주소와 취향 음식류 등의 정보를 매계변수로 전해주어 식당의 정보, 링크를 통신을 통해 받아오는 함수
        return withContext(Dispatchers.IO) {
            val clientId = "LehvnolDvQ3Dh4hnnT0E"
            val clientSecret = "6eLKj1UnTr"
            val query = q


            val apiService = ApiClient.create()
            val call = apiService.searchBlogs(clientId, clientSecret, query, 3)

            val response = call.execute() // 동기적으로 요청 실행

            val result = if (response.isSuccessful) {
                val body = response.body()
                val items = body?.items ?: emptyList()
                items.joinToString("\n") { item ->
                    "Title: ${item.title}\nLink: ${item.link}\nDescription: ${item.description}\n"
                }
            } else {
                "Error: ${response.code()}"
            }

            result
        }
    }

    suspend fun NaverApiImage(q:String): String {// 이미지 검색 인터페이스 구현
        return withContext(Dispatchers.IO) {
            val clientId = "LehvnolDvQ3Dh4hnnT0E"
            val clientSecret = "6eLKj1UnTr"
            val query = q

            val apiService = ApiClient.ImageCreate()
            val call = apiService.searchBlogs(clientId, clientSecret, query, 1)

            val response = call.execute() // 동기적으로 요청 실행

            val result = if (response.isSuccessful) {
                val body = response.body()
                val items = body?.items ?: emptyList()
                items.joinToString("\n") { item ->
                    "${item.link}\n"
                }
            } else {
                "Error: ${response.code()}"
            }

            result
        }
    }
    suspend fun GetRest(MainQuery: String, adderss: String): Restaurants {// 위의 두 함수를 사용하여 Restaurents 객체를 만들어 반환
        val NA = NaverApiMain()
        var ret = ""
        ret = NA.NaverApi(MainQuery)
        val titleRegex = Regex("Title: (.+?)\\n")
        val titles = titleRegex.findAll(ret).map { it.groupValues[1] }.toList()

        val imageUrl = mutableListOf<String>()
        titles.forEach { title ->
            val image = NA.NaverApiImage(adderss+" "+title)
            imageUrl.add(image)
        }

        val Rest = Restaurant()
        val restaurant = Rest.getRest(ret, titles, imageUrl)

        return restaurant
    }

}