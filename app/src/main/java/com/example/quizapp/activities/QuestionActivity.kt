package com.example.quizapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.adapter.OptionAdapter
import com.example.quizapp.model.Question
import com.example.quizapp.model.quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class QuestionActivity : AppCompatActivity() {
    var quizzes: MutableList<quiz>? = null
    var questions: MutableMap<String, Question>? = null

    var index = 1

    lateinit var obj: TextView
    lateinit var recyl: RecyclerView
    lateinit var btnPrev: Button
    lateinit var btnNext: Button
    lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question2)
        obj = findViewById(R.id.textoption)
        recyl = findViewById(R.id.recycler)
        btnPrev = findViewById(R.id.btnprev)
        btnNext = findViewById(R.id.btnNext)
        btnSubmit = findViewById(R.id.btnSubmit)
        //  setupbind()
        setupfirebase()
        setupEvent()
    }


    private fun setupfirebase() {
        val firestore = FirebaseFirestore.getInstance()
        val date1 = intent.getStringExtra("DATE")

        if (date1 != null) {
            val collectionReference = firestore.collection("quiz").whereEqualTo("title", date1)
            collectionReference.get().addOnSuccessListener {
                if (it != null && !it.isEmpty) {
                    quizzes = it.toObjects(quiz::class.java)

                    questions = quizzes!![0].questions
                    setupbind()

                }

            }

        }

    }


    private fun setupbind() {
        btnPrev.visibility = View.GONE
        btnPrev.visibility = View.GONE
        btnPrev.visibility = View.GONE

        if (index == 1) {
            btnNext.visibility = View.VISIBLE
        } else if(index==questions!!.size) {
            btnNext.visibility = View.GONE
            btnSubmit.visibility = View.VISIBLE
            btnPrev.visibility = View.VISIBLE

        } else {
            btnPrev.visibility = View.VISIBLE
            btnNext.visibility = View.VISIBLE
        }

//        var question15 = Question("‘India-Myanmar-Thailand Trilateral Highway’ connects which Indian state with Myanmar and Thailand?",
//           "Sikkim", "Assam","West Bengal","Manipur")


        val question15 = questions!!["question$index"]
        question15?.let {
            obj.text = question15.description
            val quesAdapter = OptionAdapter(this, question15)
            recyl.adapter = quesAdapter
            recyl.layoutManager = LinearLayoutManager(this)
            recyl.setHasFixedSize(true)


        }
    }

    fun setupEvent() {
        btnNext.setOnClickListener {
            index++
            setupbind()
        }
        btnPrev.setOnClickListener {
            index--
            setupbind()
        }
        btnSubmit.setOnClickListener {
            Log.d("Submit",questions.toString())

            val intent=Intent(this,ResultActivity::class.java)
            val json=Gson().toJson(quizzes!![0])

            intent.putExtra("Quiz",json)
            startActivity(intent)

        }

    }
}




//}


