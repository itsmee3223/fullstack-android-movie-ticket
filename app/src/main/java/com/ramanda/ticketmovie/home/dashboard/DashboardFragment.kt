package com.ramanda.ticketmovie.home.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import com.ramanda.ticketmovie.DetailActivity
import com.ramanda.ticketmovie.databinding.FragmentDashboardBinding
import com.ramanda.ticketmovie.home.model.Film
import com.ramanda.ticketmovie.utils.Preferences
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefrences: Preferences
    private lateinit var mDatabase: DatabaseReference

    private var dataList = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        prefrences = Preferences(requireActivity().applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        binding.tvNama.text = prefrences.getValues("nama")

        if(!prefrences.getValues("saldo").equals("")) {
            currency(prefrences.getValues("saldo")!!.toDouble(), binding.tvSaldo)
        }

        Glide.with(this)
            .load(prefrences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivProfilePic)

        binding.rvNowPlaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvComingSoon.layoutManager = LinearLayoutManager(requireContext().applicationContext)

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

                if(dataList.isNotEmpty()) {
                    binding.rvNowPlaying.adapter = NowPlayingAdapter(dataList){
                        startActivity(Intent(context, DetailActivity::class.java).putExtra("data", it))
                    }

                    binding.rvComingSoon.adapter = ComingSoonAdapter(dataList){
                        startActivity(Intent(context, DetailActivity::class.java).putExtra("data", it))
                    }
                }
            }

            override fun onCancelled(err: DatabaseError) {
                Toast.makeText(context, ""+err.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun currency(harga: Double, textView: TextView) {
        val localeId = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeId)
        textView.text = formatRupiah.format(harga as Double)
    }
}