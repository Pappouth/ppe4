package com.efficom.ppe4.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
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
import java.util.*

class TerminalReservationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terminal_reservation)

        val terminalName = intent.getStringExtra("name").toString()
        val terminalPrix = intent.getDoubleExtra("prix", -1.0)
        val nomBorne = findViewById<TextView>(R.id.nomBorneTextView)
        val nbBornesEditText = findViewById<EditText>(R.id.nbBornesEditTextNumber)
        var nbBornes = nbBornesEditText.text.toString().toInt()
        val plusButton = findViewById<Button>(R.id.plusButton)
        val moinsButton = findViewById<Button>(R.id.moinsButton)
        val debutCalendarView = findViewById<CalendarView>(R.id.debutCalendarView)
        val finCalendarView = findViewById<CalendarView>(R.id.finCalendarView)
        val validate = findViewById<Button>(R.id.validateButton)

        nomBorne.setText("Borne : ${terminalName}")

        // nombre de bornes
        plusButton.setOnClickListener() {
            nbBornes++
            nbBornesEditText.setText(nbBornes.toString())
        }
        moinsButton.setOnClickListener() {
            nbBornes--
            nbBornesEditText.setText(nbBornes.toString())
        }

        // récupération des dates sous le bon format
        var debutDate: String? = null
        var finDate: String? = null
        debutCalendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            debutDate = "${year}-${month}-${dayOfMonth}"
        }
        finCalendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            finDate = "${year}-${month}-${dayOfMonth}"
        }

        val prefs = getSharedPreferences("local_data", Context.MODE_PRIVATE)
        val localToken = prefs.getString("token", null)
        validate.setOnClickListener {
            if (localToken != null) {
                val d1 = debutDate
                val d2 = finDate
                if (d1 == null || d2 == null) {
                    return@setOnClickListener
                }

                // calcul du prix
                val prixProd = terminalPrix * nbBornes
                val prixTotal = terminalPrix * nbBornes

                val builder = AlertDialog.Builder(this@TerminalReservationActivity)
                val title = "Voulez-vous procéder au paiement ?"
                builder.setTitle(title)
                builder.setPositiveButton("Oui") { dialog, id ->
                    val intent = Intent(this@TerminalReservationActivity, PaymentActivity::class.java)
                    intent.putExtra("nomBorne", terminalName)
                    intent.putExtra("nbBornes", nbBornes)
                    intent.putExtra("debut", d1)
                    intent.putExtra("fin", d2)
                    intent.putExtra("prixTotal", prixTotal)
                    intent.putExtra("prixProd", prixProd)
                    startActivity(intent)
                }
                builder.setNegativeButton("Non") { dialog, id ->

                }
                builder.create().show()
            }
        }
    }
}