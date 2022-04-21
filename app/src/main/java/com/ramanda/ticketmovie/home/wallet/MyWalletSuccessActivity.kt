package com.ramanda.ticketmovie.home.wallet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ramanda.ticketmovie.databinding.ActivityMyWalletSuccessBinding
import com.ramanda.ticketmovie.home.HomeScreenActivity
import com.ramanda.ticketmovie.home.TiketScreenActivity

class MyWalletSuccessActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyWalletSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyWalletSuccessBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnHome.setOnClickListener {
            startActivity(Intent(this@MyWalletSuccessActivity, HomeScreenActivity::class.java))
        }
    }
}