package com.fordaku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.fordaku.databinding.ActivityMainBinding
import com.fordaku.fragments.BerandaFragment
import com.fordaku.fragments.FordaFragment
import com.fordaku.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(BerandaFragment())

        val beranda = BerandaFragment()
        val forda = FordaFragment()
        val profile = ProfileFragment()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.beranda -> replaceFragment(beranda)
                R.id.forda -> replaceFragment(forda)
                R.id.profil -> replaceFragment(profile)
                else -> {
                    println("ERROR Page not found.")
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}