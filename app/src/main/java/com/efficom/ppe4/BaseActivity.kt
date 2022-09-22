package com.efficom.ppe4

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.efficom.ppe4.activities.LoginActivity
import com.efficom.ppe4.activities.MainActivity
import java.util.*

open class BaseActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_user, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mainMenuItem->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.deconnectionItem-> {
                val prefs = getSharedPreferences("local_data", Context.MODE_PRIVATE)
                prefs.edit {
                    remove("token")
                    apply()
                }
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    /**
     * récupérer la date dans le format Date
     */
    fun getDate(year: Int, month: Int, day: Int): Date? {
        val cal: Calendar = Calendar.getInstance()
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, month)
        cal.set(Calendar.DAY_OF_MONTH, day)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.getTime()
    }
}