package com.ramanda.ticketmovie.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramanda.ticketmovie.databinding.ActivityCheckoutBinding
import com.ramanda.ticketmovie.model.Checkout
import com.ramanda.ticketmovie.utils.Preferences
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    private var dataList = ArrayList<Checkout>()

    private var total: Int = 0
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        preferences = Preferences(this)

        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>
//        val data = intent.getParcelableExtra<Film>("datas")

        for (a in dataList.indices){
            total += dataList[a].harga!!.toInt()
        }

        dataList.add(Checkout("Total Harus Dibayar", total.toString()))

        binding.btnBeli.setOnClickListener {
            startActivity(Intent(this@CheckoutActivity, CheckoutSuccessActivity::class.java))
        }

        binding.btnBatal.setOnClickListener {
            finish()
        }

        binding.rvCheckout.layoutManager = LinearLayoutManager(this)
        binding.rvCheckout.adapter = CheckoutAdapter(dataList){

        }

        if(preferences.getValues("saldo")!!.isNotEmpty()) {
            val localeID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            binding.tvSaldo.text = formatRupiah.format(preferences.getValues("saldo")!!.toDouble())
            binding.btnBeli.visibility = View.VISIBLE
            binding.tvFail.visibility = View.INVISIBLE

        } else {
            binding.tvSaldo.text = "Rp 0"
            binding.btnBeli.visibility = View.INVISIBLE
            binding.tvFail.visibility = View.VISIBLE
            binding.tvFail.text = "Saldo pada e-wallet kamu tidak mencukupi\n" +
                    "untuk melakukan transaksi"
        }
    }
}