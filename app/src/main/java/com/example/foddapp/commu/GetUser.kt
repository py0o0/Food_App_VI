package com.example.foddapp.commu

import android.util.Log
import retrofit2.HttpException

class GetUser {
    val apiService = RetrofitComu.client.create(ComuApi::class.java)

    suspend fun User_Data(id : String) : List<User>{// 아이디를 매계변수로 주어 서버의 데이터베이스에서 사용자의 정보를 담은 객체를 받아오는 함수
        val response = apiService.getUserById(id)
        return if (response.isSuccessful && response.body() != null) {
            response.body()!!
        } else {
            Log.e("Activity", "Response not successful or body is null")
            emptyList()
        }
    }

    suspend fun userExists(id: String): Boolean { // 아이디가 이미 존재하는 아이디인지 확인하는 함수
        return try {
            apiService.getExistsId(id)
        } catch (e: Exception) {
            Log.e("GetUser", "Error checking user existence", e)
            false
        }
    }

    suspend fun PassLogin(id: String,password: String): Boolean { // 아이디와 비밀번호가 맞는지 확인하는 함수
        return try {
            apiService.getPassLogin(id,password)
        } catch (e: Exception) {
            Log.e("GetUser", "Error checking user existence", e)
            false
        }
    }

    suspend fun createUser(user: User): User? {// 아이디와 비밀번호를 서버에 보내 데이터베이스에 등록하는 함수
        return try {
            apiService.createUser(user)
        } catch (e: HttpException) {
            Log.e("UserRepository", "HTTP error: ${e.code()} ${e.message()}")
            null
        } catch (e: Exception) {
            Log.e("UserRepository", "Error creating user", e)
            null
        }
    }

}