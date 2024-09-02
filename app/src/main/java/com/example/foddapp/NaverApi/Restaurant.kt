package com.example.foddapp.NaverApi

class Restaurant {
    fun getRest(ret:String,titles:List<String>,ImageUrl:List<String>):Restaurants{//매계변수를 받아 Restaurents객체를 만들어 반환

        val linkRegex = Regex("Link:(.+?)\\n")
        val links = linkRegex.findAll(ret).map {
            val link = it.groupValues[1].trim()
            if (link.isEmpty()|| link.isBlank()) "링크가 없음" else link
        }.toList()

        val rest = Restaurants(titles,links,ImageUrl)

        return rest
    }
}