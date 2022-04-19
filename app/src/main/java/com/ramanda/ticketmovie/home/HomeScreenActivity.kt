package com.ramanda.ticketmovie.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.ramanda.ticketmovie.R
import com.ramanda.ticketmovie.databinding.ActivityHomeScreenBinding
import com.ramanda.ticketmovie.home.dashboard.DashboardFragment
import com.ramanda.ticketmovie.home.settings.SettingFragment
import com.ramanda.ticketmovie.home.tiket.TiketFragment

class HomeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentTiket = TiketFragment()
        val fragmentSetting = SettingFragment()
        val fragmentHome = DashboardFragment()

        setFragment(fragmentHome)

        binding.ivMenu1.setOnClickListener {
            setFragment(fragmentTiket)
            changeIcon(binding.ivMenu1, R.drawable.ic_tiket_active)
            changeIcon(binding.ivMenu2, R.drawable.ic_home)
            changeIcon(binding.ivMenu3, R.drawable.ic_profile)
        }
        binding.ivMenu2.setOnClickListener {
            setFragment(fragmentHome)
            changeIcon(binding.ivMenu1, R.drawable.ic_tiket)
            changeIcon(binding.ivMenu2, R.drawable.ic_home_active)
            changeIcon(binding.ivMenu3, R.drawable.ic_profile)
        }
        binding.ivMenu3.setOnClickListener {
            setFragment(fragmentSetting)
            changeIcon(binding.ivMenu1, R.drawable.ic_tiket)
            changeIcon(binding.ivMenu2, R.drawable.ic_home)
            changeIcon(binding.ivMenu3, R.drawable.ic_profile_active)
        }
    }

    private fun changeIcon(imageView: ImageView, int: Int) {
        imageView.setImageResource(int)
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }
}