package com.example.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.adapter.Myadapter
import com.example.quizapp.model.quiz
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity()
{

    lateinit var toggle:ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var appbar2:MaterialToolbar
    lateinit var nav_view2:NavigationView
    lateinit var recycl:RecyclerView
    lateinit var firestore: FirebaseFirestore
    lateinit var Myadapter1:Myadapter
    lateinit var btndatepicker:FloatingActionButton

     var quizlist= mutableListOf<quiz>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout=findViewById(R.id.drawerlayout1)
        appbar2=findViewById(R.id.appbar2)
        nav_view2=findViewById(R.id.nav_view)
        recycl=findViewById(R.id.recyclerview)
        btndatepicker=findViewById(R.id.btnflt)
        setupview()
     //   demodata()

        setupdatepicker()
    }

    private fun setupdatepicker() {
        btndatepicker.setOnClickListener {
            val datePicker=MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager,"datepicker")

            datePicker.addOnPositiveButtonClickListener {
                Log.d("DATE","${datePicker.headerText}")

               val dateformater= SimpleDateFormat("dd-MM-yyyy")

              val date=dateformater.format(Date(it))
               Log.d("DATE",date)

                val intent= Intent(this,QuestionActivity::class.java)
                intent.putExtra("DATE",date)
                startActivity(intent)

            }
            datePicker.addOnNegativeButtonClickListener{
                Toast.makeText(this,"Nothing show",Toast.LENGTH_SHORT).show()
            }
            datePicker.addOnCancelListener {
                Toast.makeText(this,"Cancel ",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun setupfirebase() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quiz")
        collectionReference.addSnapshotListener { value, error ->

            if (value == null || error != null) {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("data", value.toObjects(quiz::class.java).toString())
            quizlist.clear()
            quizlist.addAll(value.toObjects(quiz::class.java))
            Myadapter1.notifyDataSetChanged()

        }

    }

    private fun setupRecycler() {
          Myadapter1=Myadapter(this,quizlist)
         recycl.layoutManager=GridLayoutManager(this,2)
         recycl.adapter=Myadapter1
    }

    private fun setupview() {
        setupfirebase()
       setupdrawer()
        setupRecycler()

    }


    private fun setupdrawer() {
        setSupportActionBar(appbar2)
        toggle=ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view2.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.mitem1 ->Toast.makeText(this,"Item 1 is selected",Toast.LENGTH_SHORT).show()
                R.id.mitem2 ->{ val intent=Intent(this,ProfileActivity::class.java)
                            startActivity(intent) }

                R.id.mitem3 ->Toast.makeText(this,"Item 3 is selected",Toast.LENGTH_SHORT).show()
            }
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
