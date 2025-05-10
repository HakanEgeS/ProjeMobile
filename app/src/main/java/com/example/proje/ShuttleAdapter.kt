package com.example.proje

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proje.R
import com.example.proje.model.Shuttle

class ShuttleAdapter(private val shuttleList: List<Shuttle>) :
    RecyclerView.Adapter<ShuttleAdapter.ShuttleViewHolder>() {

    class ShuttleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val tvDestination: TextView = itemView.findViewById(R.id.tvDestination)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShuttleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shuttle_item, parent, false)
        return ShuttleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShuttleViewHolder, position: Int) {
        val shuttle = shuttleList[position]
        holder.tvTime.text = shuttle.time
        holder.tvDestination.text = shuttle.destination
    }

    override fun getItemCount(): Int = shuttleList.size
}