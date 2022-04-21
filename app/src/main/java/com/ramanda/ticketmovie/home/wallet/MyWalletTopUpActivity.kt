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

        binding.ivBack.setOnClickListener {
            finish()
        }

        initListener()
    }

    private fun initListener() {
        binding.btnTopUp.setOnClickListener {
            startActivity(Intent(this, MyWalletSuccessActivity::class.java))
        }

        binding.tv10k.setOnClickListener {
            if (!status10K){
                status10K = true
                status20K = false
                status30K = false
                status40K = false
                status50K = false
                status60K = false
                binding.tv10k.setBackgroundResource(R.drawable.shape_line_pink)
                binding.tv20k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv30k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv40k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv50k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv60k.setBackgroundResource(R.drawable.shape_line_white)

                binding.btnTopUp.visibility = View.VISIBLE
                binding.etAmoung.setText("10000")
            } else {
                binding.tv10k.setBackgroundResource(R.drawable.shape_line_white)
                binding.etAmoung.setText("")
                binding.btnTopUp.visibility = View.VISIBLE
                status10K = false
            }
        }

        binding.tv20k.setOnClickListener {
            if (!status20K){
                status20K = true
                status10K = false
                status30K = false
                status40K = false
                status50K = false
                status60K = false
                binding.tv10k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv20k.setBackgroundResource(R.drawable.shape_line_pink)
                binding.tv30k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv40k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv50k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv60k.setBackgroundResource(R.drawable.shape_line_white)

                binding.btnTopUp.visibility = View.VISIBLE
                binding.etAmoung.setText("20000")
            } else {
                binding.tv20k.setBackgroundResource(R.drawable.shape_line_white)
                binding.etAmoung.setText("")
                binding.btnTopUp.visibility = View.VISIBLE
                status20K = false
            }
        }

        binding.tv30k.setOnClickListener {
            if (!status30K){
                status30K = true
                status10K = false
                status20K = false
                status40K = false
                status50K = false
                status60K = false
                binding.tv10k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv20k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv30k.setBackgroundResource(R.drawable.shape_line_pink)
                binding.tv40k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv50k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv60k.setBackgroundResource(R.drawable.shape_line_white)

                binding.btnTopUp.visibility = View.VISIBLE
                binding.etAmoung.setText("30000")
            } else {
                binding.tv30k.setBackgroundResource(R.drawable.shape_line_white)
                binding.etAmoung.setText("")
                binding.btnTopUp.visibility = View.VISIBLE
                status30K = false
            }
        }
        binding.tv40k.setOnClickListener {
            if (!status40K){
                status40K = true
                status10K = false
                status20K = false
                status30K = false
                status50K = false
                status60K = false
                binding.tv10k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv20k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv30k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv40k.setBackgroundResource(R.drawable.shape_line_pink)
                binding.tv50k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv60k.setBackgroundResource(R.drawable.shape_line_white)

                binding.btnTopUp.visibility = View.VISIBLE
                binding.etAmoung.setText("40000")
            } else {
                binding.tv40k.setBackgroundResource(R.drawable.shape_line_white)
                binding.etAmoung.setText("")
                binding.btnTopUp.visibility = View.VISIBLE
                status40K = false
            }
        }
        binding.tv50k.setOnClickListener {
            if (!status50K){
                status50K = true
                status10K = false
                status20K = false
                status30K = false
                status40K = false
                status60K = false
                binding.tv10k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv20k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv30k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv40k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv50k.setBackgroundResource(R.drawable.shape_line_pink)
                binding.tv60k.setBackgroundResource(R.drawable.shape_line_white)

                binding.btnTopUp.visibility = View.VISIBLE
                binding.etAmoung.setText("50000")
            } else {
                binding.tv50k.setBackgroundResource(R.drawable.shape_line_white)
                binding.etAmoung.setText("")
                binding.btnTopUp.visibility = View.VISIBLE
                status50K = false
            }
        }
        binding.tv60k.setOnClickListener {
            if (!status60K){
                status60K = true
                status10K = false
                status20K = false
                status30K = false
                status40K = false
                status50K = false
                binding.tv10k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv20k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv30k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv40k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv50k.setBackgroundResource(R.drawable.shape_line_white)
                binding.tv60k.setBackgroundResource(R.drawable.shape_line_pink)

                binding.btnTopUp.visibility = View.VISIBLE
                binding.etAmoung.setText("60000")
            } else {
                binding.tv60k.setBackgroundResource(R.drawable.shape_line_white)
                binding.etAmoung.setText("")
                binding.btnTopUp.visibility = View.VISIBLE
                status60K = false
            }
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
                        selectBox()
                    }
                } catch (e : NumberFormatException) {
                    selectBox()
                }
            }
        })
    }

    private fun selectBox(){
        binding.tv10k.setBackgroundResource(R.drawable.shape_line_white)
        binding.tv20k.setBackgroundResource(R.drawable.shape_line_white)
        binding.tv30k.setBackgroundResource(R.drawable.shape_line_white)
        binding.tv40k.setBackgroundResource(R.drawable.shape_line_white)
        binding.tv50k.setBackgroundResource(R.drawable.shape_line_white)
        binding.tv60k.setBackgroundResource(R.drawable.shape_line_white)
        status10K = false
        status20K = false
        status30K = false
        status40K = false
        status50K = false
        status60K = false
        binding.btnTopUp.visibility = View.INVISIBLE
    }
}