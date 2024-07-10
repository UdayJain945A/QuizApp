package com.example.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.quizapp.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class LoginIntro : AppCompatActivity() {
    lateinit var btn9:Button

    lateinit var toggle:ActionBarDrawerToggle
    lateinit var btn10:Button
    lateinit var appbar2: MaterialToolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)
        btn9=findViewById(R.id.btnstart)
        appbar2=findViewById(R.id.appbar1)
       val auth=FirebaseAuth.getInstance()
       setupView()
        auth.signOut()
        if(auth.currentUser!=null){
           Toast.makeText(this,"You are already logged in ",Toast.LENGTH_SHORT).show()
            redirect("main")
        }
        btn9.setOnClickListener {
            redirect("Login")
        }

    }

    private fun setupView() {
        setupdrawer()
    }

    private fun setupdrawer() {
        setSupportActionBar(appbar2)
        var drawerlayout=findViewById<DrawerLayout>(R.id.drawerlayout)
        var navigationView=findViewById<NavigationView>(R.id.nav_view)
        toggle= ActionBarDrawerToggle(this,drawerlayout, R.string.open, R.string.close)

        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.mitem1 ->Toast.makeText(this,"Home item is clicked",Toast.LENGTH_SHORT).show()
                R.id.mitem2 ->{
                    val intent=Intent(this,ProfileActivity::class.java)
                    startActivity(intent)

                }
                R.id.mitem3 ->Toast.makeText(this,"Share tem is clicked",Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    private  fun redirect(name:String){
        val intent=when(name) {
            "Login" -> Intent(this, Login::class.java)
            "main" -> Intent(this, MainActivity::class.java)
            else -> {
                throw Exception("no path exist")
            }
        }
            startActivity(intent)
        finish()

    }

   override fun onOptionsItemSelected(item: MenuItem): Boolean {

     if(toggle.onOptionsItemSelected(item)){
         return true
     }
       return super.onOptionsItemSelected(item)
    }
}