package com.fordaku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(private val posts: ArrayList<Post>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPostTitle: TextView
        val tvPostDate: TextView
        val tvPostFordaName: TextView
        init {
            // Define click listener for the ViewHolder's View.
            tvPostTitle = view.findViewById(R.id.tvPostTitle)
            tvPostDate = view.findViewById(R.id.tvPostDate)
            tvPostFordaName = view.findViewById(R.id.tvPostFordaName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_layout2, parent, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val currentItem = posts[position]
        holder.tvPostTitle.text = currentItem.title
        holder.tvPostDate.text = currentItem.created
        holder.tvPostFordaName.text = currentItem.fordaId
    }

    override fun getItemCount() = posts.size
}