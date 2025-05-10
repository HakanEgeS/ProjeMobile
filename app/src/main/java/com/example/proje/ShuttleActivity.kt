package com.example.proje

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proje.R
import com.example.proje.model.Shuttle
import com.example.proje.ShuttleAdapter
import kotlinx.coroutines.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class ShuttleActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShuttleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shuttle)

        recyclerView = findViewById(R.id.recyclerViewShuttle)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchShuttleData()
    }

    private fun fetchShuttleData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("http://10.0.2.2:5000/shuttle")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"

                val response = connection.inputStream.bufferedReader().use { it.readText() }
                val jsonArray = JSONArray(response)

                val shuttleList = mutableListOf<Shuttle>()
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)
                    val shuttle = Shuttle(
                        id = item.getInt("id"),
                        time = item.getString("time"),
                        destination = item.getString("destination")
                    )
                    shuttleList.add(shuttle)
                }

                withContext(Dispatchers.Main) {
                    adapter = ShuttleAdapter(shuttleList)
                    recyclerView.adapter = adapter
                }

            } catch (e: Exception) {
                Log.e("ShuttleFetchError", "Hata: ${e.message}")
            }
        }
    }
}