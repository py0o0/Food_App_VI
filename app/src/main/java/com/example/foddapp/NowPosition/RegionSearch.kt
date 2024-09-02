package com.example.foddapp.NowPosition

import android.location.Address
import android.location.Geocoder
import android.util.Log
import java.io.IOException

class RegionSearch {

    fun getAddress(lat: Double, lng: Double,geocoder: Geocoder):String { // 위도와 경도를 받아 주소로 변환
        var address: List<Address>? = null
        try {
            address = geocoder.getFromLocation(lat, lng, 10)
        } catch (e: IOException) {
            e.printStackTrace()
            Log.d("test", "입출력오류")
        }
        if (address != null) {
            if (address.size == 0) {
                return "error"
            } else {
                val thoroughfare = address[0]?.thoroughfare.toString()
                return thoroughfare
            }
        }
        return "error"
    }
}