package com.efficom.ppe4.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.efficom.ppe4.BaseActivity
import com.efficom.ppe4.R
import com.efficom.ppe4.api.Service
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PaymentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val nomBorne = intent.getStringExtra("nomBorne").toString()
        val nbBornes = intent.getIntExtra("nbBornes", 1)
        val debut = intent.getStringExtra("debut").toString()
        val fin = intent.getStringExtra("fin").toString()
        val prixTotal = intent.getDoubleExtra("prixTotal", 1.0)
        val prixProd = intent.getDoubleExtra("prixProd",1.0)

        val prefs = getSharedPreferences("local_data", Context.MODE_PRIVATE)
        val localToken = prefs.getString("token", null)

        if(localToken != null) {
            val validatePaymentButton = findViewById<Button>(R.id.validatePaymentButton)
            val retrofit = Retrofit.Builder()
                .baseUrl("https://efficomtest.alwaysdata.net/api_ppe4/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(Service::class.java)
            val resaDateCall = service.resaDates(localToken, nomBorne, nbBornes, debut, fin, prixTotal, prixProd)

            validatePaymentButton.setOnClickListener() {
                resaDateCall.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        val builder = AlertDialog.Builder(this@PaymentActivity)
                        val title = "Confirmer la réservation ?"
                        builder.setTitle(title)
                        builder.setPositiveButton("Oui") { dialog, id ->
                            Toast.makeText(this@PaymentActivity, "La borne a bien été réservée", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@PaymentActivity, ReservationsListActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        builder.setNegativeButton("Non") { dialog, id ->

                        }
                        builder.create().show()
                    }
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.e("PPE_LOG", "error")
                    }
                })
            }
        }else{
            Log.w("PPE_LOG", "token error")
        }
    }
}

