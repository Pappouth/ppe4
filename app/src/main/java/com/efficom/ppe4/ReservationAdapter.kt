package com.efficom.ppe4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReservationAdapter(val reservations: List<TerminalReservation>): RecyclerView.Adapter<ReservationAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val titleTextView = view.findViewById<TextView>(R.id.reservationListItemTitleTextView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val view = inflater.inflate(R.layout.reservation_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val r = reservations[position]
        viewHolder.titleTextView.text = "commande n°${r.id} / date : ${r.date} / prix : ${r.prix}€"
    }

    override fun getItemCount(): Int {
        return reservations.count()
    }
}