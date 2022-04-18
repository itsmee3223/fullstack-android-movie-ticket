package com.ramanda.ticketmovie.checkout
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ramanda.ticketmovie.databinding.ActivityCheckoutSuccessBinding
import com.ramanda.ticketmovie.model.Checkout
import com.ramanda.ticketmovie.utils.Preferences

class CheckoutSuccessActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutSuccessBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnHome.setOnClickListener {
            finish()
        }
    }
}