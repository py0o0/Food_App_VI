package com.example.foddapp.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.foddapp.NaverApi.Restaurants
import com.example.foddapp.R
import com.example.foddapp.commu.User
import com.example.foddapp.databinding.ActivityFoodSearchBinding


class FoodSearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFoodSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_food_search)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_food_search)
        binding.lifecycleOwner = this

        val restaurant = intent.getSerializableExtra("restaurant") as? Restaurants
        var SkyState = intent.getSerializableExtra("SkyState") as String
        var Temperature  = intent.getSerializableExtra("Temperature") as String
        var thoroughfare  = intent.getSerializableExtra("thoroughfare") as String
        var User  = intent.getSerializableExtra("User") as List<User>

        val imageUrl1 = restaurant?.image?.getOrNull(0)
        val imageUrl2 = restaurant?.image?.getOrNull(1)
        val imageUrl3 = restaurant?.image?.getOrNull(2)

        restaurant?.let {
            // restaurant 객체에서 title과 link 리스트 가져오기
            val titles = it.title
            val links = it.link

            // 첫 번째 TextView에 텍스트 설정
            binding.titleTextView1.text = titles.getOrNull(0) ?: "No Title"
            binding.linkTextView1.text = links.getOrNull(0) ?: "No Link"

            // 두 번째 TextView에 텍스트 설정
            binding.titleTextView2.text = titles.getOrNull(1) ?: "No Title"
            binding.linkTextView2.text = links.getOrNull(1) ?: "No Link"

            // 세 번째 TextView에 텍스트 설정
            binding.titleTextView3.text = titles.getOrNull(2) ?: "No Title"
            binding.linkTextView3.text = links.getOrNull(2) ?: "No Link"

        }
        Glide.with(this)
            .load(imageUrl1)
            .into(binding.imageTextView1)
        Glide.with(this)
            .load(imageUrl2)
            .into(binding.imageTextView2)
        Glide.with(this)
            .load(imageUrl3)
            .into(binding.imageTextView3)

        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("SkyState", SkyState)
            intent.putExtra("Temperature", Temperature)
            intent.putExtra("thoroughfare", thoroughfare)
            intent.putExtra("User", ArrayList(User))
            startActivity(intent)
        }

    }
}