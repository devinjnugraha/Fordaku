package com.fordaku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.fordaku.databinding.ActivityMainBinding
import com.fordaku.fragments.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val berandaFragment = BerandaFragment()
        val fordaFragment = FordaFragment()

        replaceFragment(berandaFragment)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.beranda -> replaceFragment(berandaFragment)
                R.id.forda -> replaceFragment(fordaFragment)
                R.id.profil -> replaceFragment(getProfileFragment())
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        if (binding.bottomNavigationView.menu.findItem(R.id.profil).isChecked) {
            replaceFragment(getProfileFragment())
        }
    }

    private fun getProfileFragment() : Fragment  {
        return if (Firebase.auth.currentUser == null) ProfileGuestFragment()
        else ProfileAuthFragment()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}