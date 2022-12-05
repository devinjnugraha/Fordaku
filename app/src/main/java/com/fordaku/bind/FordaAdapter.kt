package com.fordaku.bind

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.fordaku.model.Posts
import com.fordaku.R
import com.fordaku.model.Forda
import com.google.firebase.firestore.CollectionReference
import kotlinx.android.synthetic.main.recyclerview_layout.view.*
import kotlinx.android.synthetic.main.recyclerview_layout2.view.*
import java.text.SimpleDateFormat
import java.util.*

class FordaAdapter(
    private val context: Context,
    private val collection: CollectionReference,
    options: FirestoreRecyclerOptions<Forda>
) : FirestoreRecyclerAdapter<Forda, FordaAdapter.FordaViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FordaViewHolder {
        return FordaViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_layout, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: FordaViewHolder, position: Int, forda: Forda) {
        viewHolder.bindItem(forda)
    }

    class FordaViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(forda: Forda) {
            view.apply {
                tvNameForda.text = forda.strName
                tvLocationForda.text = forda.strLocation
            }
        }
    }
}