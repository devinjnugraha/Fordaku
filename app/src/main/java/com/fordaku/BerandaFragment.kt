package com.fordaku

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
import com.fordaku.bind.AllPostAdapter
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class BerandaFragment : Fragment() {
    private lateinit var mAdapter: FirestoreRecyclerAdapter<Posts, AllPostAdapter.PostsViewHolder>
    private val mFirestore = FirebaseFirestore.getInstance()
    private val mPostsCollection = mFirestore.collection("posts")
    private val mQuery = mPostsCollection.orderBy("intCreatedAt", Query.Direction.ASCENDING)

    private lateinit var adapter: PostAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var posts: ArrayList<Posts>

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
            layoutManager = LinearLayoutManager(activity)
        }

        val options = FirestoreRecyclerOptions.Builder<Posts>()
            .setQuery(mQuery, Posts::class.java)
            .build()

        mAdapter = AllPostAdapter(requireActivity(), mPostsCollection, options)
        mAdapter.notifyDataSetChanged()
        rv.adapter = mAdapter
//        dataInitialize()
//        val layoutManager = LinearLayoutManager(context)
//        adapter = PostAdapter(posts)
//        recyclerView = view.findViewById(R.id.rvBeranda)
//
//        recyclerView.layoutManager = layoutManager
//        recyclerView.adapter = adapter
//        recyclerView.setHasFixedSize(true)
//
        view.findViewById<MaterialButton>(R.id.postButton).setOnClickListener {
            startActivity(Intent(activity, CreatePostActivity::class.java))
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

    private fun dataInitialize() {
        // TODO: Integrate with database and add profile photo to FORDA data class and RecyclerView
        posts = arrayListOf<Posts>()

//        posts.add(Posts("Jombang Menjadi Kota yang Paling Berdesa", "","Jombang Merdeka"))
//        posts.add(Posts("Pantai Pulau Merah Semakin Ramai Pengunjung!", "","Banyuwangi Maju"))
//        posts.add(Posts("Etika Profesi Malang Semakin Bobrok!", "", "Forum Malang Raya"))
//        posts.add(Posts("Tanah Bumbu Membumbu Buta di Tanah yang Berbumbu Sangat Banyak", "", "Warga Banua Berbumbu Raya"))
//        posts.add(Posts("Etika Profesi Malang Semakin Bobrok!", "", "Forum Malang Raya"))
//        posts.add(Posts("Pantai Pulau Merah Semakin Ramai Pengunjung!", "", "Banyuwangi Maju"))
//        posts.add(Posts("Jombang Menjadi Kota yang Paling Berdesa", "", "Jombang Merdeka"))
//        posts.add(Posts("Etika Profesi Malang Semakin Bobrok!", "", "Forum Malang Raya"))
    }

}