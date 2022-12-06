package com.fordaku.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.fordaku.R

class PostDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<TextView>(R.id.titleTextView).text = intent.getStringExtra("title")
        findViewById<TextView>(R.id.contentTextView).text = intent.getStringExtra("content")
        findViewById<TextView>(R.id.dateTextView).text = intent.getStringExtra("date")

    }
}