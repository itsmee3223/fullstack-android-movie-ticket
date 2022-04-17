package com.ramanda.ticketmovie.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.ramanda.ticketmovie.PilihBangkuActivity
import com.ramanda.ticketmovie.R
import com.ramanda.ticketmovie.databinding.ActivityDetailBinding
import com.ramanda.ticketmovie.model.Film
import com.ramanda.ticketmovie.model.Plays

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<Plays>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val data = intent.getParcelableExtra<Film>("data")
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
            .child(data?.judul.toString())
            .child("play")

        binding.tvTitle.text = data?.judul
        binding.tvGenre.text = data?.genre
        binding.tvDesc.text = data?.desc
        binding.tvRate.text = data?.rating

        Glide.with(this)
            .load(data?.poster)
            .into(binding.ivPoster)

        binding.btnPilihBangku.setOnClickListener {
            startActivity(Intent(this@DetailActivity, PilihBangkuActivity::class.java).putExtra("data", data))
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.rvWhoPlay.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (data in snapshot.children) {
                    val film = data.getValue(Plays::class.java)
                    dataList.add(film!!)
                }

                binding.rvWhoPlay.adapter = PlaysAdapter(dataList){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}