package com.fordaku.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fordaku.R
import com.fordaku.activities.LoginActivity
import com.fordaku.activities.RegisterActivity
import com.google.android.material.button.MaterialButton

class ProfileGuestFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_guest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialButton>(R.id.registerButton).setOnClickListener {
            startActivity(Intent(activity, RegisterActivity::class.java))
        }

        view.findViewById<TextView>(R.id.loginTextView).setOnClickListener {
            startActivity(Intent(activity, LoginActivity::class.java))
        }
    }
}