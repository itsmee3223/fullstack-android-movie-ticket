package com.ramanda.ticketmovie.home

import android.app.Dialog
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
import com.ramanda.ticketmovie.databinding.ActivityTiketBinding
import com.ramanda.ticketmovie.home.tiket.TiketAdapter
import com.ramanda.ticketmovie.model.Checkout
import com.ramanda.ticketmovie.model.Film

class TiketScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTiketBinding

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTiketBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val data = intent.getParcelableExtra<Film>("data")

        binding.tvTitle.text = data!!.judul
        binding.tvGenre.text = data.genre
        binding.tvRate.text = data.rating

        Glide.with(this)
            .load(data.poster)
            .into(binding.ivPosterImage)

        binding.rcCheckout.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("A3","70000"))
        dataList.add(Checkout("A4","70000"))

        binding.rcCheckout.adapter = TiketAdapter(dataList) {
        }

        binding.ivClose.setOnClickListener {
            finish()
        }

        binding.ivBarcode.setOnClickListener {
            showDialog("Silahkan melakukan scanning pada counter tiket terdekat")
        }

    }

    private fun showDialog(title: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_qr)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        val tvDesc = dialog.findViewById(R.id.tv_desc) as TextView
        tvDesc.text = title

        val btnClose = dialog.findViewById(R.id.btn_close) as Button
        btnClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }
}