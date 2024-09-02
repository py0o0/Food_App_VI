package com.example.foddapp.Weather

import java.util.Calendar

class WeatherDate { // 기상청에게 보낼 시각의 양식을 맞추는 작업
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)

    val NowDate = year * 10000 + month * 100 + day


    fun BaseTime():String {
        if (hour < 5)
            return "0200"
        else if (hour < 8)
            return "0500"
        else if (hour < 11)
            return "0800"
        else if (hour < 14)
            return "1100"
        else if (hour < 17)
            return "1400"
        else if (hour < 20)
            return "1700"
        else if (hour < 23)
            return "2000"
        return "2300"
    }


}