package com.fordaku.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fordaku.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class BerandaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onResume() {
        super.onResume()
        val user = Firebase.auth.currentUser

        var fragment: Fragment
        if (user != null) {
            fragment = BerandaAuthFragment()
        }
        else {
            fragment = BerandaGuestFragment()
        }
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }
}