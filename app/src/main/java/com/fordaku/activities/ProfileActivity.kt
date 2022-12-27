package com.fordaku.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.fordaku.R
import com.fordaku.model.Forda
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(findViewById(R.id.toolbar))

        val user = Firebase.auth.currentUser
        val db = FirebaseFirestore.getInstance()
        if (user != null) {
            db.collection("fordas")
                .document(user.uid)
                .get()
                .addOnSuccessListener {
                    val forda = it.toObject<Forda>()!!
                    findViewById<TextView>(R.id.emailTextView).text = forda.strEmail
                    findViewById<TextView>(R.id.nameTextView).text = forda.strName
                    findViewById<TextView>(R.id.locationTextView).text = forda.strLocation
                    findViewById<TextView>(R.id.descriptionTextView).text = forda.strDescription
                }
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