package com.example.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.quizapp.R
import com.google.firebase.auth.FirebaseAuth

class Sign : AppCompatActivity() {


    lateinit var firebaseAuth: FirebaseAuth
    lateinit var email2:EditText
    lateinit var pass2:EditText
    lateinit var pass3:EditText
    lateinit var txtsign:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        val btn3=findViewById<Button>(R.id.btn2)
        email2=findViewById<EditText>(R.id.email2)
        pass2 =findViewById<EditText>(R.id.Pass2)
         pass3=findViewById<EditText>(R.id.pass90)
        txtsign=findViewById(R.id.txt3)
        Log.d("<<<>>>","hiii");
         firebaseAuth =FirebaseAuth.getInstance()
        btn3.setOnClickListener {
            sign()
        }
        txtsign.setOnClickListener{
            val intent=Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun sign(){
        val eml2=email2.text.toString()
        val pss2=pass2.text.toString()
        val cnps=pass3.text.toString()
        if(eml2.isBlank() || pss2.isBlank()){
            Toast.makeText(this,"Email and password is empty",Toast.LENGTH_SHORT).show()
            return
        }
        if(pss2!=cnps){
            Toast.makeText(this,"Password is not correct",Toast.LENGTH_SHORT).show()
            return
        }
       firebaseAuth.createUserWithEmailAndPassword(eml2,pss2)
           .addOnCompleteListener(this){
          if(it.isSuccessful){

              Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()

          }
            else{
                          Toast.makeText(this,"Login failed",Toast.LENGTH_SHORT).show()
          }
            }
       }

    }