package com.example.proje

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proje.R
import com.example.proje.model.Announcement
import kotlinx.coroutines.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class AnnouncementActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnnouncementAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_announcement)

        recyclerView = findViewById(R.id.recyclerViewAnnouncements)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchAnnouncements()
    }

    private fun fetchAnnouncements() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("http://10.0.2.2:5000/announcements")
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "GET"

                val response = conn.inputStream.bufferedReader().use { it.readText() }
                val jsonArray = JSONArray(response)

                val list = mutableListOf<Announcement>()
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val item = Announcement(
                        id = obj.getInt("id"),
                        title = obj.getString("title"),
                        content = obj.getString("content"),
                        date = obj.getString("date")
                    )
                    list.add(item)
                }

                withContext(Dispatchers.Main) {
                    adapter = AnnouncementAdapter(list)
                    recyclerView.adapter = adapter
                }

            } catch (e: Exception) {
                Log.e("AnnFetchError", "Hata: ${e.message}")
            }
        }
    }
}