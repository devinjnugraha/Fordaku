package com.fordaku.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.fordaku.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class CreatePostActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        findViewById<MaterialButton>(R.id.submitButton).setOnClickListener {
            val title = findViewById<TextInputEditText>(R.id.titleTextView).text.toString()
            val content = findViewById<TextInputEditText>(R.id.contentTextView).text.toString()

            val user = Firebase.auth.currentUser
            if (user != null) {
                val db = FirebaseFirestore.getInstance()

                val post = hashMapOf(
                    "strTitle" to title,
                    "strContent" to content,
                    "intCreatedAt" to System.currentTimeMillis() / 1000,
                    "userId" to (user.uid)
                )
                db.collection("posts")
                    .add(post)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Berhasil membuat post", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Gagal membuat post: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        findViewById<ImageView>(R.id.backImageView).setOnClickListener {
            finish()
        }
    }
}