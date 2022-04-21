package com.ramanda.ticketmovie.checkout

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramanda.ticketmovie.R
import com.ramanda.ticketmovie.databinding.ActivityCheckoutBinding
import com.ramanda.ticketmovie.home.TiketScreenActivity
import com.ramanda.ticketmovie.model.Checkout
import com.ramanda.ticketmovie.model.Film
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
        val data = intent.getParcelableExtra<Film>("datas")

        for (a in dataList.indices){
            total += dataList[a].harga!!.toInt()
        }

        dataList.add(Checkout("Total Harus Dibayar", total.toString()))

        binding.btnBeli.setOnClickListener {
            startActivity(Intent(this@CheckoutActivity, CheckoutSuccessActivity::class.java))
            if (data != null) {
                showNotif(data)
            }
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

    private fun showNotif(datas: Film) {
        val NOTIFICATION_CHANNEL_ID = "channel_raa_notif"
        val context = this.applicationContext
        var notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channelName = "TiketMovie Notif Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val mChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance)
            notificationManager.createNotificationChannel(mChannel)
        }

        val mIntent = Intent(this, TiketScreenActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("data", datas)
        mIntent.putExtras(bundle)

        val pendingIntent =
            PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        builder.setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.logo_mov)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    this.resources,
                    R.drawable.logo_notification
                )
            )
            .setTicker("notif tiket movie starting")
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setLights(Color.RED, 3000, 3000)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentTitle("Sukses Terbeli")
            .setContentText("Tiket "+datas.judul+" berhasil kamu dapatkan. Enjoy the movie!")

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(115, builder.build())
    }
}