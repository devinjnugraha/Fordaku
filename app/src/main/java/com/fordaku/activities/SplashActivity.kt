package com.fordaku.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.fordaku.MainActivity
import com.fordaku.R

class SplashActivity : AppCompatActivity() {
    //Deklarasi variabel timer Splash Screen muncul
    private val SPLASH_TIME_OUT:Long = 500 // delay 2 detik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Kode untuk menjalankan main screen timer splash screen habis
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}