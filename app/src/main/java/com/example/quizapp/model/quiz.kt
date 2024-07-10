package com.example.quizapp.model

data class quiz(
    var id:String="",
    var title: String="",
    var questions:MutableMap<String,Question> = mutableMapOf()
)
