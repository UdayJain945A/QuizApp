package com.example.quizapp.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.activities.QuestionActivity
import com.example.quizapp.databinding.ItemView1Binding
import com.example.quizapp.databinding.ItemViewBinding
import com.example.quizapp.model.quiz
import com.example.quizapp.util.Colorpicker
import com.example.quizapp.util.iconpicker

class Myadapter(var context: Context,var quizes:List<quiz>):RecyclerView.Adapter<Myadapter.MyViewholder>() {


    inner class MyViewholder(itemView: View):RecyclerView.ViewHolder(itemView){

        var Tittle=itemView.findViewById<TextView>(R.id.textviewer1)
        var img=itemView.findViewById<ImageView>(R.id.imgviewer1)
        var card_contain=itemView.findViewById<CardView>(R.id.cardview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        var View9:View=LayoutInflater.from(context).inflate(R.layout.item_view1,parent,false)
        return MyViewholder(View9)
    }

    override fun getItemCount(): Int {
       return quizes.size
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.Tittle.text = quizes[position].title
        holder.img.setImageResource(iconpicker.geticon())
        holder.card_contain.setCardBackgroundColor(Color.parseColor(Colorpicker.getcolor()))

        holder.itemView.setOnClickListener {
            val intent= Intent(context,QuestionActivity::class.java)
            intent.putExtra("DATE",quizes[position].title)
            context.startActivity(intent)


        }
    }
}