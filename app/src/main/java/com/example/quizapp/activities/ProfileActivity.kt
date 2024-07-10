package com.example.quizapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.R
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    lateinit var firebaseAuth:FirebaseAuth
    lateinit var txtemail:TextView
    lateinit var btnlogout:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile2)
        firebaseAuth=FirebaseAuth.getInstance()
        txtemail=findViewById(R.id.txtEmail)
        txtemail.text=firebaseAuth.currentUser?.email
        btnlogout=findViewById(R.id.btnLogout)

        btnlogout.setOnClickListener {
            firebaseAuth.signOut()
            Toast.makeText(this,"Logout",Toast.LENGTH_SHORT).show()
            val intent= Intent(this,Login::class.java)
            startActivity(intent)


        }

    }
}