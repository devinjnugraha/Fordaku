package com.fordaku.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.ImageView
import android.widget.Toast
import com.fordaku.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var nameEditText: TextInputEditText
    private lateinit var locationEditText: TextInputEditText
    private lateinit var descriptionEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var passwordConfirmationEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        nameEditText = findViewById(R.id.nameEditText)
        locationEditText = findViewById(R.id.locationEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        passwordConfirmationEditText = findViewById(R.id.passwordConfirmationEditText)

        findViewById<ImageView>(R.id.backImageView).setOnClickListener {
            finish()
        }

        findViewById<MaterialButton>(R.id.registerButton).setOnClickListener {
            val name = nameEditText.text.toString()
            val location = locationEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val passwordConfirmation = passwordConfirmationEditText.text.toString()

            if (name.isNullOrEmpty()) {
                nameEditText.error = "Field should not be empty."
                return@setOnClickListener
            }
            if (location.isNullOrEmpty()) {
                locationEditText.error = "Field should not be empty."
                return@setOnClickListener
            }
            if (description.isNullOrEmpty()) {
                descriptionEditText.error = "Field should not be empty."
                return@setOnClickListener
            }

            if (email.isNullOrEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEditText.error = "Email is not in a valid format."
                return@setOnClickListener
            }

            if (password.length < 6) {
                passwordEditText.error = "Password must consist of 6 or more characters."
                return@setOnClickListener
            }

            if (!password.equals(passwordConfirmation)) {
                passwordEditText.error = "Password is not the same."
                return@setOnClickListener
            }

            val auth = Firebase.auth
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        if (user != null) {
                            user.updateProfile(userProfileChangeRequest {
                                displayName = name
                            }).addOnCompleteListener {
                                val db = FirebaseFirestore.getInstance()

                                val forda = hashMapOf(
                                    "strName" to name,
                                    "strLocation" to location,
                                    "strDescription" to description,
                                    "strEmail" to email,
                                    "intCreatedAt" to System.currentTimeMillis() / 1000,
                                    "userId" to (user.uid)
                                )
                                db.collection("fordas")
                                    .add(forda)
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "Register berhasil!", Toast.LENGTH_SHORT).show()
                                        finish()
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(this, "Register gagal: ${it.message}", Toast.LENGTH_SHORT).show()
                                    }

                                finish()
                            }
                        }
                        else {
                            Toast.makeText(this, "Error: " + task.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        Toast.makeText(this, "Error creating User: " + task.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}