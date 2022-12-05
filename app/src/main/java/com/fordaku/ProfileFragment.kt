package com.fordaku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

private const val ARG_NAME = "name"
private const val ARG_EMAIL = "email"

class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_NAME)
            param2 = it.getString(ARG_EMAIL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, param1)
                    putString(ARG_EMAIL, param2)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        val user = FirebaseViewModel.auth.currentUser

        var fragment: Fragment
        if (user != null) {
            fragment = ProfileAuthFragment()
            val bundle = Bundle()
            bundle.putString("name", user.displayName)
            bundle.putString("email", user.email)
            fragment.arguments = bundle
        }
        else {
            fragment = ProfileGuestFragment()
        }
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }
}