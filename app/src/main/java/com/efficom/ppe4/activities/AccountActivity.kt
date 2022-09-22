package com.efficom.ppe4.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.efficom.ppe4.BaseActivity
import com.efficom.ppe4.R
import com.efficom.ppe4.api.Service
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AccountActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        /*val mailTextView = findViewById<TextView>(R.id.mailTextView)

        val prefs = getSharedPreferences("local_data", Context.MODE_PRIVATE)
        val localToken = prefs.getString("token", null)
        if(localToken != null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://efficomtest.alwaysdata.net/api_ppe4/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(Service::class.java)
            val accountCall = service.infoCompte(localToken)
            accountCall.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    val accountInfoList = response.body()
                    if (accountInfoList != null) {
//                        val infoList = List<AccountInfo>()
//                        val infoList = listOf(accountInfoList)
//                        val infoList: List<AccountInfo> = listOf(lesInfo)
//                        val jsonRoot = JSONObject(accountInfoList)
//                        mailTextView.text = "${accountInfoList}"
                    } else {
                        Log.w("PPE_LOG", "Failure")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("PPE_LOG", "Failure")
                }
            })
        }*/
    }
}