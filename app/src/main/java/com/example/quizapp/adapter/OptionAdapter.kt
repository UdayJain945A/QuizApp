package com.example.quizapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.model.Question

class OptionAdapter(var context:Context,var data:Question):RecyclerView.Adapter<OptionAdapter.OptionViewholder>(){

    class OptionViewholder(view:View):RecyclerView.ViewHolder(view) {
        val option1=view.findViewById<TextView>(R.id.textoption1)

    }
    var optionlist= listOf<String>(data.option1,data.option2,data.option3,data.option4)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewholder {
       val view1=LayoutInflater.from(context).inflate(R.layout.item_view,parent,false)
        return OptionViewholder(view1)
    }

    override fun getItemCount(): Int {
          return optionlist.size

    }

    override fun onBindViewHolder(holder: OptionViewholder, position: Int) {
       holder.option1.text=optionlist[position]

       holder.itemView.setOnClickListener(View.OnClickListener {

           data.useranswer=optionlist[position]
           notifyDataSetChanged()
       })

        if(data.useranswer==optionlist[position]){

            holder.itemView.setBackgroundResource(R.drawable.option_itemselectedbg)
        }
        else{
           holder.itemView.setBackgroundResource(R.drawable.option_itembg)
        }
    }

}


