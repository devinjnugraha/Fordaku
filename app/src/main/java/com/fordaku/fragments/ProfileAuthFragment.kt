package com.fordaku.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.fordaku.R
import com.fordaku.activities.ProfileActivity
import com.fordaku.bind.ProfilePostAdapter
import com.fordaku.model.Posts
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase

class ProfileAuthFragment : Fragment() {
    private lateinit var mAdapter: FirestoreRecyclerAdapter<Posts, ProfilePostAdapter.PostsViewHolder>
    private val mFirestore = FirebaseFirestore.getInstance()
    private val mPostsCollection = mFirestore.collection("posts")
    private lateinit var mQuery : Query

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = Firebase.auth.currentUser
        if (user != null) {
            mQuery = mPostsCollection
                .whereEqualTo("userId", user.uid)
                .orderBy("intCreatedAt", Query.Direction.DESCENDING)

            view.findViewById<TextView>(R.id.nameTextView).text = user.displayName
            view.findViewById<TextView>(R.id.emailTextView).text = user.email
        }

        val rv = view.findViewById<RecyclerView>(R.id.rvProfil)
        rv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }

        val options = FirestoreRecyclerOptions.Builder<Posts>()
            .setQuery(mQuery, Posts::class.java)
            .build()

        mAdapter = ProfilePostAdapter(requireActivity(), mPostsCollection, options)
        mAdapter.notifyDataSetChanged()
        rv.adapter = mAdapter
        view.findViewById<MaterialButton>(R.id.profilButton).setOnClickListener {
            startActivity(Intent(activity, ProfileActivity::class.java))
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