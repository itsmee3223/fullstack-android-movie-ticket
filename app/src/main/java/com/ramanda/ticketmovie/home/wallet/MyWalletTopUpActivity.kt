package com.ramanda.ticketmovie.home.wallet

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ramanda.ticketmovie.R
import com.ramanda.ticketmovie.databinding.ActivityMyWalletTopUpBinding

class MyWalletTopUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyWalletTopUpBinding

    private var status10K : Boolean = false
    private var status20K : Boolean = false
    private var status30K : Boolean = false
    private var status40K : Boolean = false
    private var status50K : Boolean = false
    private var status60K : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyWalletTopUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initListener()
    }

    private fun initListener() {
        binding.btnTopUp.setOnClickListener {
            startActivity(Intent(this, MyWalletSuccessActivity::class.java))
        }
        binding.tv10k.setOnClickListener {
            if(status10K) {
                deselectMoney(binding.tv10k)
                return@setOnClickListener
            }
            selectMoney(binding.tv10k)
        }

        binding.etAmoung.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) { }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {  }

            override fun afterTextChanged(s: Editable) {
                try {
                    if (s.toString().toInt() >= 10000) {
                        binding.btnTopUp.visibility = View.VISIBLE
                    } else {
                        binding.tv10k.setTextColor(resources.getColor(R.color.white))
                        binding.tv10k.setBackgroundResource(R.drawable.shape_line_white)
                        status10K = false
                        binding.btnTopUp.visibility = View.INVISIBLE
                    }
                } catch (e : NumberFormatException) {
                    binding.tv10k.setTextColor(resources.getColor(R.color.white))
                    binding.tv10k.setBackgroundResource(R.drawable.shape_line_white)
                    status10K = false
                    binding.btnTopUp.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun selectMoney(textView:TextView){
        textView.setTextColor(resources.getColor(R.color.white))
        textView.setBackgroundResource(R.drawable.shape_line_pink)
        status10K = true

        binding.btnTopUp.visibility = View.VISIBLE
        binding.etAmoung.setText("10000")
    }

    private fun deselectMoney(textView:TextView){
        textView.setTextColor(resources.getColor(R.color.white))
        textView.setBackgroundResource(R.drawable.shape_line_white)
        status10K = false

        binding.btnTopUp.visibility = View.INVISIBLE
        binding.etAmoung.setText("")
    }
}