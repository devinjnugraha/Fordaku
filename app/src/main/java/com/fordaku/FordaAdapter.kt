package com.fordaku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FordaAdapter(private val fordas: ArrayList<Forda>) :
    RecyclerView.Adapter<FordaAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNameForda: TextView
        val tvLocationForda: TextView
        init {
            // Define click listener for the ViewHolder's View.
            tvNameForda = view.findViewById(R.id.tvNameForda)
            tvLocationForda = view.findViewById(R.id.tvLocationForda)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_layout, parent, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val currentItem = fordas[position]
        holder.tvNameForda.text = currentItem.name
        holder.tvLocationForda.text = currentItem.location
    }

    override fun getItemCount() = fordas.size
}