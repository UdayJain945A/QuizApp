package com.example.quizapp.util

import android.graphics.Color
import com.example.quizapp.util.Colorpicker.color

object Colorpicker {

    val color= arrayOf("#3685BC","D36280","#E44F55","FA8056","818BCA","#7D659F","#51BAB3","#4FB66C","E3AD17",
        "#3EB9DF","#3685BC","D36280","#E44F55","FA8056","818BCA","#7D659F","#51BAB3","#4FB66C","E3AD17")

    var currentcolor=0

    fun getcolor() :String{


        val randomColor = Color.rgb(
            (0..255).random(),
            (0..255).random(),
            (0..255).random()
        )
        return String.format("#%06X", 0xFFFFFF and randomColor)





//        try{
//            {
//                currentcolor = (currentcolor + 1) % color.size
//
//
//            }
//
//        }catch (e:Exception){
//            return "68a0b0"
//        }

    }
}