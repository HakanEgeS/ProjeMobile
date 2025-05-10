package com.example.proje

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proje.R
import com.example.proje.model.Announcement

class AnnouncementAdapter(private val announcementList: List<Announcement>) :
    RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder>() {

    class AnnouncementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvContent: TextView = itemView.findViewById(R.id.tvContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.announcement_item, parent, false)
        return AnnouncementViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
        val item = announcementList[position]
        holder.tvTitle.text = item.title
        holder.tvDate.text = item.date
        holder.tvContent.text = item.content
    }

    override fun getItemCount(): Int = announcementList.size
}