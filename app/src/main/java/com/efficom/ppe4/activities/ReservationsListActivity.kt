package com.efficom.ppe4.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.efficom.ppe4.BaseActivity
import com.efficom.ppe4.R
import com.efficom.ppe4.ReservationAdapter
import com.efficom.ppe4.TerminalReservation
import com.efficom.ppe4.api.Service
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ReservationsListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservations_list)

        val prefs = getSharedPreferences("local_data", Context.MODE_PRIVATE)
        val localToken = prefs.getString("token", null)
        if(localToken != null) {
            val retrofit = Retrofit.Builder()
            .baseUrl("https://efficomtest.alwaysdata.net/api_ppe4/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

            val service = retrofit.create(Service::class.java)
            val resaCall = service.reservationsList(localToken)
            resaCall.enqueue(object : Callback<List<TerminalReservation>> {
                override fun onResponse(call: Call<List<TerminalReservation>>, response: Response<List<TerminalReservation>>) {
                    val resaList = response.body()
                    if(resaList != null){
                        val adapter = ReservationAdapter(resaList)
                        val recyclerView = findViewById<RecyclerView>(R.id.reservationsRecyclerView)

                        recyclerView.adapter = adapter
                        recyclerView.layoutManager = LinearLayoutManager(this@ReservationsListActivity)
                    }else{
                        Log.d("PPE_LOG", "error2")
                    }
                }

                override fun onFailure(call: Call<List<TerminalReservation>>, t: Throwable) {
                    Log.e("PPE_LOG", "error")
                }
            })
        }else{
            Log.w("PPE_LOG", "token error")
        }
    }
}