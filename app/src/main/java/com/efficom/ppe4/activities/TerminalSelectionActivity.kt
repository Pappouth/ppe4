package com.efficom.ppe4.activities

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.efficom.ppe4.*
import com.efficom.ppe4.api.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TerminalSelectionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terminal_selection)


        val retrofit = Retrofit.Builder()
        .baseUrl("https://efficomtest.alwaysdata.net/api_ppe4/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        val service = retrofit.create(Service::class.java)
        val termCall = service.terminalsList()
        termCall.enqueue(object : Callback<List<Terminal>> {
            override fun onResponse(call: Call<List<Terminal>>, response: Response<List<Terminal>>) {
                val termList = response.body()
                if(termList != null){
                    val terminalSelectionAdapter = TerminalSelectionAdapter(termList)
                    val recyclerView = findViewById<RecyclerView>(R.id.terminalListRecyclerView)

                    recyclerView.adapter = terminalSelectionAdapter
                    recyclerView.layoutManager = LinearLayoutManager(this@TerminalSelectionActivity)
                }else{
                    Log.d("PPE_LOG", "error2")
                }
            }

            override fun onFailure(call: Call<List<Terminal>>, t: Throwable) {
                Log.e("PPE_LOG", "error")
            }
        })
    }
}