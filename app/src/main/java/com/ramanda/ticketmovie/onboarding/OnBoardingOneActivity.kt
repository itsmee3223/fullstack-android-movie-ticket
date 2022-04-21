package com.ramanda.ticketmovie.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ramanda.ticketmovie.sign.signin.SignInActivity
import com.ramanda.ticketmovie.databinding.ActivityOnBoardingOneBinding
import com.ramanda.ticketmovie.utils.Preferences

class OnboardingOneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingOneBinding
    private lateinit var preferences: Preferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingOneBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        preferences = Preferences(this)

        if(preferences.getValues("onboarding").equals("1")){
            finishAffinity()
            startActivity(Intent(this@OnboardingOneActivity, SignInActivity::class.java))
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this@OnboardingOneActivity,
                OnBoardingTwoActivity::class.java)
            startActivity(intent)
        }

        binding.btnSkip.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@OnboardingOneActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
