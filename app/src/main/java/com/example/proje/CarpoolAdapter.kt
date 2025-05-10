package com.example.proje

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proje.R
import com.example.proje.model.Carpool

class CarpoolAdapter(private val carpoolList: List<Carpool>) :
    RecyclerView.Adapter<CarpoolAdapter.CarpoolViewHolder>() {

    class CarpoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvDestination: TextView = itemView.findViewById(R.id.tvDestination)
        val tvContact: TextView = itemView.findViewById(R.id.tvContact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarpoolViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.carpool_item, parent, false)
        return CarpoolViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarpoolViewHolder, position: Int) {
        val carpool = carpoolList[position]
        holder.tvName.text = carpool.name
        holder.tvDestination.text = carpool.destination
        holder.tvContact.text = carpool.contact
    }

    override fun getItemCount(): Int = carpoolList.size
}