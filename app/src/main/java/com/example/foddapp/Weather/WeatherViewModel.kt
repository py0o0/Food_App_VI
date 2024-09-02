package com.example.foddapp.Weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import retrofit2.Response
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weatherResponse : MutableLiveData<Response<Weather>> = MutableLiveData()
    val weatherResponse get() = _weatherResponse

    fun getWeather(dataType : String, numOfRows : Int, pageNo : Int,
                   baseDate : Int, baseTime : String, nx : String, ny : String){
        viewModelScope.launch {
            val response = repository.getWeather(dataType, numOfRows, pageNo, baseDate, baseTime, nx, ny)
            _weatherResponse.value = response
        }
    }
}