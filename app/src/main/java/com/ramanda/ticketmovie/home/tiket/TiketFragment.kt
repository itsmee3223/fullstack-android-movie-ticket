package com.ramanda.ticketmovie.home.tiket

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.ramanda.ticketmovie.databinding.FragmentTiketBinding
import com.ramanda.ticketmovie.home.TiketScreenActivity
import com.ramanda.ticketmovie.home.dashboard.ComingSoonAdapter
import com.ramanda.ticketmovie.model.Film
import com.ramanda.ticketmovie.utils.Preferences
import kotlin.collections.ArrayList

class TiketFragment : Fragment() {
    private var _binding: FragmentTiketBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefrences: Preferences
    private lateinit var mDatabase: DatabaseReference

    private var dataList = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTiketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        prefrences = Preferences(requireActivity().applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        binding.rcTiket.layoutManager = LinearLayoutManager(requireContext().applicationContext)

        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()

                for(getDataSnap in dataSnapshot.children) {
                    val film = getDataSnap.getValue(Film::class.java)
                    dataList.add(film!!)
                }

               binding.rcTiket.adapter = ComingSoonAdapter(dataList){
                   startActivity(Intent(context, TiketScreenActivity::class.java).putExtra("data", it))
               }

                binding.tvTotal.text = dataList.size.toString() + " Movies"
            }

            override fun onCancelled(err: DatabaseError) {
                Toast.makeText(context, ""+err.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}