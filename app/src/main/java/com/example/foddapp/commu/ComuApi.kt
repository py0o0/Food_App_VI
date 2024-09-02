package com.example.foddapp.commu

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
//스프링 서버에서 값을 가져오는 인터페이스
interface ComuApi {
    @GET("/api/users/preferences")
    suspend fun getUserById(
        @Query("id") id: String, // 매계변수
    ): Response<List<User>>//Call<List<Food>> 반환객체

    @GET("/api/foodsRandomByCategory")
    suspend fun getFoodsRandomByCategory(
        @Query("cate1") cate1: String,
        @Query("cate2") cate2: String
    ): Food//Call<List<Food>>

    @GET("/api/users/existsId")
    suspend fun getExistsId(
        @Query("id") id: String
    ): Boolean//Call<List<Food>>

    @GET("/api/users/Login")
    suspend fun getPassLogin(
        @Query("id") id: String,
        @Query("password") password: String
    ): Boolean//Call<List<Food>>

    @POST("/api/users/create") //정보를 보내는 인터페이스
    suspend fun createUser(@Body user: User): User
}