package com.efficom.ppe4.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.efficom.ppe4.R
import com.efficom.ppe4.api.Service
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("local_data", Context.MODE_PRIVATE)
        val localToken = prefs.getString("token", null)
        if(localToken != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        setContentView(R.layout.activity_login)

        val retrofit = Retrofit.Builder()
        .baseUrl("https://efficomtest.alwaysdata.net/api_ppe4/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        val loginEditText = findViewById<EditText>(R.id.loginEditText)
        val passwdEditText = findViewById<EditText>(R.id.passwordEditText)
        val connexionButton = findViewById<Button>(R.id.connexionButton)

        connexionButton.setOnClickListener {
            val service = retrofit.create(Service::class.java)
            val loginCall = service.login(loginEditText.text.toString(), passwdEditText.text.toString())
            loginCall.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    val token = response.body()?.string()
                    if (token != null){
                        Log.d("PPE_LOG", "The token is $token")
                        prefs.edit {
                            putString("token",token)
                            apply()
                        }
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.w("PPE_LOG", "Login failed")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("PPE_LOG", "Login failed")
                }
            })
        }
    }
}