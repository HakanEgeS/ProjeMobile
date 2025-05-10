package com.example.proje

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnShuttle = findViewById<Button>(R.id.btnShuttle)
        val btnCarpool = findViewById<Button>(R.id.btnCarpool)
        val btnMap = findViewById<Button>(R.id.btnMap)
        val btnAnnouncements = findViewById<Button>(R.id.btnAnnouncements)

        btnAnnouncements.setOnClickListener {
            val intent = Intent(this, AnnouncementActivity::class.java)
            startActivity(intent)
        }

        btnShuttle.setOnClickListener {
            val intent = Intent(this, ShuttleActivity::class.java)
            startActivity(intent)
        }

        btnCarpool.setOnClickListener {
            val intent = Intent(this, CarpoolActivity::class.java)
            startActivity(intent)
        }

        btnMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }
}