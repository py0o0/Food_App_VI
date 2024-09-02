package com.example.foddapp.commu

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 스프링 서버의 링크를 저장하고 통신을 준비
object RetrofitComu {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://port-0-foodspring-ss7z32llwd0bh6v.sel5.cloudtype.app/"
    //https://port-0-foodspring-ss7z32llwd0bh6v.sel5.cloudtype.app/

    val client: Retrofit
        get() { //client가 호출될 때 실행되는 문
            if (retrofit == null) { // 싱글톤, 필요할 때만 제작
                retrofit = Retrofit.Builder() //retrofit을 새로 만들고 링크 삽입과 받은 json데이터를 코틀린객체로 변환
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
}