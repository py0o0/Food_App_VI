package com.example.foddapp.Weather

import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Response

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {

    suspend fun getWeather(
        dataType : String, numOfRows : Int, pageNo : Int,
        baseDate : Int, baseTime : String, nx : String, ny : String) : Response<Weather> {
        return weatherApi.getWeather(dataType,numOfRows,pageNo,baseDate,baseTime,nx,ny)
    }
}