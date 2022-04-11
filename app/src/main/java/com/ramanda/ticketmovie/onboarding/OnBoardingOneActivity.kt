package com.ramanda.ticketmovie.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ramanda.ticketmovie.sign.SignInActivity
import com.ramanda.ticketmovie.databinding.ActivityOnBoardingOneBinding

class OnboardingOneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingOneBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingOneBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnHome.setOnClickListener {
            val intent = Intent(this@OnboardingOneActivity,
                OnBoardingTwoActivity::class.java)
            startActivity(intent)
        }

        binding.btnDaftar.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@OnboardingOneActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
