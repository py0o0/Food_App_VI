package com.example.foddapp.commu

import kotlin.random.Random

class GetFoodCate {
    val noodle = "면"
    val rice = "밥"
    val soup   = "국물"

    fun WeatherFood(SkyState:String, Temperature:String) :String{



        val ran= Random.nextInt(1, 11)
        val Ntem = when {
            Temperature.toDouble() < 5 -> "추움"
            Temperature.toDouble() < 25 -> "따뜻"
            else -> "더움"
        }

        val Nwea = when (SkyState.toInt()){
            0 -> "맑음"
            1,2,4 -> "비"
            else -> "눈"
        }


        if(Nwea == "맑음"&&Ntem == "더움")   //맑음 - 더움
            return Get(soup,noodle,rice,ran)

        else if(Nwea == "맑음" && Ntem == "따뜻")//맑음 - 따뜻
            return Get(noodle,rice,soup,ran)

        else if(Nwea == "맑음" && Ntem == "추움")//맑음 - 추움
            return Get(rice,noodle,soup,ran)

        else if(Nwea == "비" && Ntem == "더움")//비 - 더움
            return Get(rice,soup,noodle,ran)

        else if(Nwea == "비" && Ntem == "따뜻")//비 - 따뜻
            return Get(rice,soup,noodle,ran)

        else if(Nwea == "비" && Ntem == "추움")//비 - 추움
            return Get(soup,noodle,rice,ran)

        else if(Nwea == "눈" && Ntem == "더움")//눈 - 더움
            return Get(noodle,rice,soup,ran)

        else if(Nwea == "눈" && Ntem == "따뜻")//눈 - 따뜻
            return Get(rice,noodle,soup,ran)

        else (Nwea == "눈" && Ntem == "추움")//눈 - 추움
        return Get(soup,rice,noodle,ran)

    }
    fun Get(a:String, b:String, c:String,n:Int) :String{
        if(n<6)
            return a
        else if(n<9)
            return b
        else
            return c
    }



    fun PreFood (one:String, two:String, three:String,four:String):String{
        val randomNum = Random.nextInt(10)
        return when{
            randomNum < 4 -> one
            randomNum < 7 -> two
            randomNum < 9 -> three
            else -> four
        }
    }

}