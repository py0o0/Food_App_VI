package com.example.foddapp.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.foddapp.commu.GetUser
import com.example.foddapp.commu.User
import com.example.foddapp.databinding.ActivityJoinBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class JoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.exist.setOnClickListener {
            val id = binding.id.text.toString()
            GlobalScope.launch(Dispatchers.Main){
                val GU = GetUser()
                val exist = GU.userExists(id)
                if(exist){
                    Toast.makeText(this@JoinActivity,"계정이 이미 존재합니다", Toast.LENGTH_LONG).show()
                }
                else
                    Toast.makeText(this@JoinActivity,"사용 가능합니다.", Toast.LENGTH_LONG).show()
            }
        }

        binding.crate.setOnClickListener {
            val id = binding.id.text.toString()

            GlobalScope.launch(Dispatchers.Main){
                val GU = GetUser()
                val exist = GU.userExists(id)
                if(exist){
                    Toast.makeText(this@JoinActivity,"계정이 이미 존재합니다", Toast.LENGTH_LONG).show()
                }
                else{
                    val password = binding.password.text.toString()

                    val foodlist = arrayOf("한식", "중식", "양식", "일식")

                    val one = binding.one.text.toString()
                    val two = binding.two.text.toString()
                    val three = binding.three.text.toString()
                    val four = binding.four.text.toString()

                    val Set = setOf(one, two, three, four)

                    if(one in foodlist &&two in foodlist &&three in foodlist &&four in foodlist && Set.size == 4) {
                        GU.createUser(User(id, password, one, two, three, four))

                        Toast.makeText(this@JoinActivity, "계정 생성 성공", Toast.LENGTH_LONG).show()

                        val intent = Intent(this@JoinActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else
                        Toast.makeText(this@JoinActivity,"선호 양식이 잘못 작성되었습니다", Toast.LENGTH_LONG).show()
                }

            }
        }

        binding.down.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}