package com.example.proje


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proje.R
import com.example.proje.model.Carpool
import kotlinx.coroutines.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class CarpoolActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarpoolAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carpool)

        recyclerView = findViewById(R.id.recyclerViewCarpool)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchCarpoolData()
    }

    private fun fetchCarpoolData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("http://10.0.2.2:5000/carpool")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"

                val response = connection.inputStream.bufferedReader().use { it.readText() }
                val jsonArray = JSONArray(response)

                val carpoolList = mutableListOf<Carpool>()
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val carpool = Carpool(
                        id = obj.getInt("id"),
                        name = obj.getString("name"),
                        destination = obj.getString("destination"),
                        contact = obj.getString("contact")
                    )
                    carpoolList.add(carpool)
                }

                withContext(Dispatchers.Main) {
                    adapter = CarpoolAdapter(carpoolList)
                    recyclerView.adapter = adapter
                }

            } catch (e: Exception) {
                Log.e("CarpoolFetchError", "Hata: ${e.message}")
            }
        }
    }
}