package com.fordaku

import FirebaseViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.google.android.material.button.MaterialButton

class ProfileActivity : AppCompatActivity() {
    private val firebaseViewModel: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(findViewById(R.id.toolbar))

        val user = firebaseViewModel.getUser()
        if (user != null) {
            findViewById<TextView>(R.id.emailTextView).text = user.email
            findViewById<TextView>(R.id.nameTextView).text = user.displayName
        }

        findViewById<MaterialButton>(R.id.logoutButton).setOnClickListener {
            FirebaseViewModel.auth.signOut()
            finish()
        }

        findViewById<ImageView>(R.id.backImageView).setOnClickListener {
            finish()
        }
    }
}