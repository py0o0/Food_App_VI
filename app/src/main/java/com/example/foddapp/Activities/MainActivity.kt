package com.example.foddapp.Activities

import com.example.foddapp.NaverApi.NaverApiMain
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.foddapp.NowPosition.RequestPermissionsUtil
import com.example.foddapp.R
import com.example.foddapp.commu.GetFood
import com.example.foddapp.commu.GetFoodCate
import com.example.foddapp.commu.GetUser
import com.example.foddapp.commu.User
import com.example.foddapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    fun calcul() {
        var SkyState = intent.getSerializableExtra("SkyState") as String
        var Temperature  = intent.getSerializableExtra("Temperature") as String
        var thoroughfare  = intent.getSerializableExtra("thoroughfare") as String
        var User  = intent.getSerializableExtra("User") as List<User>
        //로그인 액티비티에서 저장한 날씨, 현재 위치, 사용자 정보들을 활용하지만 음식과 식당은 매번 다르게 뽑히도록 메인에서 설정
        GlobalScope.launch(Dispatchers.Main) {
            val getFood = GetFoodCate()

            var Foodcate1 = getFood.PreFood(User[0].one, User[0].two, User[0].three, User[0].four)
            var Foodcate2 = getFood.WeatherFood(SkyState, Temperature)

            val GF = GetFood()
            val Food = GF.FoodData(Foodcate1, Foodcate2)

            val restQuery = thoroughfare + " " + Food.food

            val NA = NaverApiMain()
            val restaurant = NA.GetRest(restQuery, thoroughfare)

            val intent = Intent(this@MainActivity, FoodSearchActivity::class.java)
            intent.putExtra("restaurant", restaurant)
            intent.putExtra("SkyState", SkyState)
            intent.putExtra("Temperature", Temperature)
            intent.putExtra("thoroughfare", thoroughfare)
            intent.putExtra("User", ArrayList(User))
            startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 데이터바인딩
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this

        val btn = binding.locationButton

        val locationButton: Button = findViewById(R.id.locationButton)
        locationButton.setOnClickListener {
            calcul()
        }
    }

    override fun onStart() {
        super.onStart()
        RequestPermissionsUtil(this).requestLocation() // 위치 권한 요청
    }

}