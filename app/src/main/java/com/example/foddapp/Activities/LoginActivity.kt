package com.example.foddapp.Activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.foddapp.NowPosition.RegionSearch
import com.example.foddapp.NowPosition.WeatherLocation
import com.example.foddapp.Weather.WeatherDate
import com.example.foddapp.Weather.WeatherViewModel
import com.example.foddapp.commu.GetUser
import com.example.foddapp.databinding.ActivityLoginBinding
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private val viewModel by viewModels<WeatherViewModel>()
    private val weatherlocation = WeatherLocation()
    var SkyState = ""
    var Temperature = ""

    data class XY(var nx :Int, var ny : Int )

    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {//위치 권한 흭득 후 액티비티 이동
                NextAcitivity()
            } else {
                Toast.makeText(this, "위치 권한이 필요합니다.", Toast.LENGTH_LONG).show()
            }
        }

    @SuppressLint("MissingPermission")
    private fun NextAcitivity() {

        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) { //위치권한 확인
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { success: Location? ->
                    success?.let { location ->
                        val xy = weatherlocation.convertToXy(35.7912, 127.1208) //위도, 경도를 좌표값으로 변환
                        val weatherdate = WeatherDate()
                        viewModel.getWeather(
                            "JSON",
                            14,
                            1,
                            weatherdate.NowDate,
                            weatherdate.BaseTime(),
                            xy.nx.toString(),
                            xy.ny.toString()
                        )
                        viewModel.weatherResponse.observe(this) {
                            for (i in it.body()?.response!!.body.items.item) {
                                when (i.category) {
                                    "PTY" -> SkyState = i.fcstValue
                                    "TMP" -> Temperature = i.fcstValue //검색에 필요한 온도와 하늘 상태만 저장
                                }
                            }

                            GlobalScope.launch(Dispatchers.Main) {
                                val GU = GetUser()
                                val user = GU.User_Data("aa")

                                val Region = RegionSearch()
                                val geocoder = Geocoder(this@LoginActivity)
                                val thoroughfare = Region.getAddress(35.7912, 127.1208, geocoder)

                                val intent = Intent(this@LoginActivity, MainActivity::class.java)

                                //로그인 액티비티에서 미리 날씨, 현재 위치, 사용자 정보들을 미리 계산한 후 앱이 꺼질 때 까지 그 정보를 계속 사용
                                intent.putExtra("SkyState", SkyState)
                                intent.putExtra("Temperature", Temperature)
                                intent.putExtra("thoroughfare", thoroughfare)
                                intent.putExtra("User", ArrayList(user))

                                startActivity(intent)
                                finish()
                            }
                        }
                    }
                }
                .addOnFailureListener { fail -> }
        }
        else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            // EditText에서 값을 가져옴
            val id = binding.id.text.toString()
            val password = binding.password.text.toString()

            GlobalScope.launch(Dispatchers.Main){
                val GU = GetUser()
                val login = GU.PassLogin(id, password)
                if(login){
                    Toast.makeText(this@LoginActivity,"로그인 성공", Toast.LENGTH_LONG).show()
                    NextAcitivity()
                }
                else
                    Toast.makeText(this@LoginActivity,"로그인 실패", Toast.LENGTH_LONG).show()

            }

        }
        binding.join.setOnClickListener{
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}