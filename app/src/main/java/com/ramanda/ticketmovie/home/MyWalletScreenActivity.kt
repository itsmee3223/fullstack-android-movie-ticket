package com.ramanda.ticketmovie.home

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ramanda.ticketmovie.R
import com.ramanda.ticketmovie.databinding.ActivityMyWalletBinding
import com.ramanda.ticketmovie.databinding.ActivityTiketBinding
import com.ramanda.ticketmovie.home.tiket.TiketAdapter
import com.ramanda.ticketmovie.home.wallet.MyWalletTopUpActivity
import com.ramanda.ticketmovie.home.wallet.WalletAdapter
import com.ramanda.ticketmovie.model.Checkout
import com.ramanda.ticketmovie.model.Film
import com.ramanda.ticketmovie.model.Wallet

class MyWalletScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyWalletBinding

    private var dataList = ArrayList<Wallet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyWalletBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.ivBack.setOnClickListener {
            finish()
        }

        loadDummyData()
    }

    private fun loadDummyData() {
        dataList.add(
            Wallet(
                "Avengers Returns",
                "Sabtu 12 Jan, 2020",
                700000.0,
                "0"
            )
        )
        dataList.add(
            Wallet(
                "Top Up",
                "Sabtu 12 Jan, 2020",
                1700000.0,
                "1"
            )
        )
        dataList.add(
            Wallet(
                "Avengers Returns",
                "Sabtu 12 Jan, 2020",
                700000.0,
                "0"
            )
        )

        initListener()
    }

    private fun initListener() {
        binding.rvTransaksi.layoutManager = LinearLayoutManager(this)
        binding.rvTransaksi.adapter = WalletAdapter(dataList){

        }

        binding.btnTopUp.setOnClickListener {
            startActivity(Intent(this, MyWalletTopUpActivity::class.java))
        }
    }
}