package com.ramanda.ticketmovie.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ramanda.ticketmovie.R
import com.ramanda.ticketmovie.databinding.ActivityPilihBangkuBinding
import com.ramanda.ticketmovie.model.Checkout
import com.ramanda.ticketmovie.model.Film

class PilihBangkuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPilihBangkuBinding

    private var statusA3: Boolean = false
    private var statusA4: Boolean = false
    private var total: Int = 0

    private var dataList = ArrayList<Checkout>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPilihBangkuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val data = intent.getParcelableExtra<Film>("data")

        binding.tvTitle.text = data!!.judul

        binding.ivKursiA3.setOnClickListener {
            if (statusA3) {
                binding.ivKursiA3.setImageResource(R.drawable.ic_rectangle_empty)
                statusA3 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("A3", "70000"))

            } else {
                binding.ivKursiA3.setImageResource(R.drawable.ic_rectangle_selected)
                statusA3 = true
                total +=1
                belitiket(total)
                dataList.add(Checkout("A3", "70000"))
            }
        }

        binding.ivKursiA4.setOnClickListener {
            if (statusA4) {
                binding.ivKursiA4.setImageResource(R.drawable.ic_rectangle_empty)
                statusA4 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("A4", "70000"))

            } else {
                binding.ivKursiA4.setImageResource(R.drawable.ic_rectangle_selected)
                statusA4 = true
                total +=1
                belitiket(total)
                dataList.add(Checkout("A4", "70000"))
            }
        }

        binding.btnBeli.setOnClickListener {

            val intent = Intent(
                this,
                CheckoutActivity::class.java
            ).putExtra("data", dataList).putExtra("datas", data)
            startActivity(intent)
        }

    }

    private fun belitiket(total:Int) {
        if (total == 0) {
            binding.btnBeli.text = "Beli Tiket"
            binding.btnBeli.visibility = View.INVISIBLE
        } else {
            binding.btnBeli.text = "Beli Tiket ($total)"
            binding.btnBeli.visibility = View.VISIBLE
        }

    }
}