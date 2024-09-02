package com.example.foddapp.commu

import java.io.Serializable

// 서버에서 받을 User 객체
data class User(val id: String,
                val password: String,
                val one: String,
                val two: String,
                val three: String,
                val four: String) : Serializable
