package com.fordaku

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.fordaku.bind.ProfilePostAdapter
import com.fordaku.model.Posts
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase

private const val ARG_NAME = "name"
private const val ARG_EMAIL = "email"

class ProfileAuthFragment : Fragment() {
    private var name: String? = null
    private var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_NAME)
            email = it.getString(ARG_EMAIL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_auth, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(name: String, email: String) =
            ProfileGuestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, name)
                    putString(ARG_EMAIL, email)
                }
            }
    }



    private lateinit var mAdapter: FirestoreRecyclerAdapter<Posts, ProfilePostAdapter.PostsViewHolder>
    private val mFirestore = FirebaseFirestore.getInstance()
    private val mPostsCollection = mFirestore.collection("posts")
    private lateinit var mQuery : Query

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = Firebase.auth.currentUser
        if (user != null) {
            mQuery = mPostsCollection
                .whereEqualTo("userId", user.uid)
                .orderBy("intCreatedAt", Query.Direction.ASCENDING)
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

        view.findViewById<TextView>(R.id.nameTextView).text = name
        view.findViewById<TextView>(R.id.emailTextView).text = email
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