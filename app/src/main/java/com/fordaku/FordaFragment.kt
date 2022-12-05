package com.fordaku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fordaku.model.Forda

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FordaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FordaFragment : Fragment() {
    // TODO: Add profile photo to Forda data class and RecyclerView
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter: FordaAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var fordas: ArrayList<Forda>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forda, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FordaFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FordaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        adapter = FordaAdapter(fordas)
        recyclerView = view.findViewById(R.id.rvForda)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
    }

    // Check RecyclerView with dummy data -devin
    private fun dataInitialize() {
        // TODO: Integrate with database and add profile photo to FORDA data class and RecyclerView
        fordas = arrayListOf<Forda>()

        fordas.add(Forda("Forda Malang", "Kota Malang, Jawa Timur"))
        fordas.add(Forda("Forda Surabaya", "Kota Surabaya, Jawa Timur"))
        fordas.add(Forda("Forda Jakarta", "Kota Jakarta Selatan, DKI JAKARTA"))
        fordas.add(Forda("Jombang Merdeka", "Lawan Penjajah Jombang!"))
    }
}