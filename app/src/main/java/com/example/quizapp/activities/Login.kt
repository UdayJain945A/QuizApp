package com.example.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.quizapp.R
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
         lateinit var firebaseAuth: FirebaseAuth
         lateinit var email:EditText
         lateinit var password:EditText
         lateinit var btn: Button
         lateinit var txt:TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth=FirebaseAuth.getInstance()
        email=findViewById(R.id.email3)
        password=findViewById(R.id.pass3)
        btn=findViewById(R.id.btn3)
        txt=findViewById(R.id.txt4)
        btn.setOnClickListener {
            login()
        }
        txt.setOnClickListener{
            val intent=Intent(this, Sign::class.java)
            startActivity(intent)
            finish()
        }



    }
    private fun login(){
        val eml=email.text.toString()
        val pss=password.text.toString()
        if(eml.isBlank() || pss.isBlank()){
            Toast.makeText(this,"Email and password is empty",Toast.LENGTH_SHORT).show()
            return
        }


        firebaseAuth.signInWithEmailAndPassword(eml,pss).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this,"Succes Login",Toast.LENGTH_SHORT).show()
                val intent=Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this,"Login Fail",Toast.LENGTH_SHORT).show()
            }
        }

    }

    }
