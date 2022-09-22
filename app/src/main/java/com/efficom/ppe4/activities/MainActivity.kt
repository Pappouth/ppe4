package com.efficom.ppe4.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.core.content.edit
import com.efficom.ppe4.BaseActivity
import com.efficom.ppe4.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resaListButton = findViewById<Button>(R.id.resaListButton)
        val addResaButton = findViewById<Button>(R.id.addResaButton)
        val accountButton = findViewById<Button>(R.id.accountButton)

        resaListButton.setOnClickListener{
            val intent = Intent(this, ReservationsListActivity::class.java)
            startActivity(intent)
        }
        addResaButton.setOnClickListener{
            val intent = Intent(this, TerminalSelectionActivity::class.java)
            startActivity(intent)
        }
        accountButton.setOnClickListener{
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
    }
}

