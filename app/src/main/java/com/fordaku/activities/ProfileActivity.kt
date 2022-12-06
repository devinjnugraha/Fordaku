package com.fordaku.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.fordaku.R
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(findViewById(R.id.toolbar))

        val user = Firebase.auth.currentUser
        if (user != null) {
            findViewById<TextView>(R.id.emailTextView).text = user.email
            findViewById<TextView>(R.id.nameTextView).text = user.displayName
        }

        findViewById<MaterialButton>(R.id.logoutButton).setOnClickListener {
            Firebase.auth.signOut()
            finish()
        }

        findViewById<ImageView>(R.id.backImageView).setOnClickListener {
            finish()
        }
    }
}