package com.example.quizapp.util

import com.example.quizapp.R

object iconpicker {
    var icon= arrayOf(
        R.drawable.icon1,
        R.drawable.icon2,
        R.drawable.icon3,
        R.drawable.icon4,
        R.drawable.icon5,
        R.drawable.icon3,
        R.drawable.icon1
    )
    var currenticon=0
    fun geticon():Int{
        currenticon= (currenticon+1)%icon.size
        return icon[currenticon]
    }
}