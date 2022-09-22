package com.efficom.ppe4

import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efficom.ppe4.activities.ReservationInfoActivity
import com.efficom.ppe4.activities.TerminalReservationActivity

class TerminalSelectionAdapter(val terminals: List<Terminal>): RecyclerView.Adapter<TerminalSelectionAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView = view.findViewById<TextView>(R.id.nameTerminalTextView)
        val prodImageView = view.findViewById<ImageView>(R.id.prodImageView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val view = inflater.inflate(R.layout.terminal_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val r = terminals[position]
        Glide.with(viewHolder.prodImageView.context).load(r.image).into(viewHolder.prodImageView)
        viewHolder.titleTextView.text = "${r.nom} : ${r.prix}€"
        viewHolder.itemView.setOnClickListener(){
            val intent= Intent(viewHolder.itemView.context, TerminalReservationActivity::class.java)
            intent.putExtra("name", r.nom)
            intent.putExtra("prix", r.prix)
            viewHolder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return terminals.count()
    }
}