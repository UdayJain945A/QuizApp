package com.example.quizapp.activities

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.R
import com.example.quizapp.model.quiz
import com.google.gson.Gson

class ResultActivity : AppCompatActivity() {
    lateinit var quiz1:quiz
    lateinit var txtscore:TextView
    lateinit var txtAnswer:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result2)
        txtscore=findViewById(R.id.txtScore)
        txtAnswer=findViewById(R.id.txtAnswer)

        setUpResult()

    }

    private fun setUpResult() {

        val quizData=intent.getStringExtra("Quiz")

         quiz1=Gson().fromJson<quiz>(quizData,quiz::class.java)

        calculateScore()

        setAnswerview()



    }

    private fun setAnswerview() {
        val builder = StringBuilder("")
        for (entry in quiz1.questions.entries) {
            val question = entry.value
            builder.append("<font color'#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#009688'>Answer: ${question.answer}</font><br/><br/>")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            txtAnswer.text = Html.fromHtml(builder.toString());
        }
    }

    private fun calculateScore() {
        var score=0
       for(entry in quiz1.questions.entries){
           val question=entry.value
           if(question.answer==question.useranswer){
               score+=10
           }
       }
        txtscore.text="Score is $score"


    }
}