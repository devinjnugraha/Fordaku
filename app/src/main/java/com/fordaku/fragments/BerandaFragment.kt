package com.fordaku.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.fordaku.R
import com.fordaku.activities.CreatePostActivity
import com.fordaku.bind.AllPostAdapter
import com.fordaku.model.Posts
import com.fordaku.utils.LinearLayoutManagerWrapper
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase

class BerandaFragment : Fragment() {
    private lateinit var mAdapter: FirestoreRecyclerAdapter<Posts, AllPostAdapter.PostsViewHolder>
    private val mFirestore = FirebaseFirestore.getInstance()
    private val mPostsCollection = mFirestore.collection("posts")
    private val mQuery = mPostsCollection.orderBy("intCreatedAt", Query.Direction.DESCENDING)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = view.findViewById<RecyclerView>(R.id.rvBeranda)
        rv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManagerWrapper(activity, LinearLayoutManager.VERTICAL, false)
        }

        val options = FirestoreRecyclerOptions.Builder<Posts>()
            .setQuery(mQuery, Posts::class.java)
            .build()

        mAdapter = AllPostAdapter(requireActivity(), mPostsCollection, options)
        mAdapter.notifyDataSetChanged()
        rv.adapter = mAdapter

        val postButton = view.findViewById<MaterialButton>(R.id.postButton)
        postButton.setOnClickListener {
            startActivity(Intent(activity, CreatePostActivity::class.java))
        }
        if (Firebase.auth.currentUser == null) {
            postButton.visibility = View.GONE
        }
    }

    override fun onStart() {
        super.onStart()
        mAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        mAdapter.stopListening()
    }

}