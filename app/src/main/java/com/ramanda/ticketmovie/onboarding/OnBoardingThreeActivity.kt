package com.ramanda.ticketmovie.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ramanda.ticketmovie.sign.SignInActivity
import com.ramanda.ticketmovie.databinding.ActivityOnBoardingThreeBinding

class OnBoardingThreeActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityOnBoardingThreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingThreeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnHome.setOnClickListener{
            startActivity(Intent(this@OnBoardingThreeActivity, SignInActivity::class.java))
        }
    }
}