package com.ramanda.ticketmovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ramanda.ticketmovie.onboarding.OnBoardingOneActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashScreenActivity, OnBoardingOneActivity::class.java)
                startActivity(intent)
                finish()
        }, 2000)
    }
}