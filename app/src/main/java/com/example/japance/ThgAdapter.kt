package com.example.japance

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ThgAdapter(private val thingList: ArrayList<Thing>) :
    RecyclerView.Adapter<ThgAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentThg = thingList[position]
        holder.tvThgName.text = currentThg.name
        holder.tvThgScore.text = currentThg.score.toString()
    }

    override fun getItemCount(): Int {
        return thingList.size
    }

    class ViewHolder(iteView: View) : RecyclerView.ViewHolder(iteView) {
        val tvThgName : TextView = itemView.findViewById(R.id.tvThgName)
        val tvThgScore : TextView = itemView.findViewById(R.id.tvThgScore)
    }
}