package com.ramanda.ticketmovie.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ramanda.ticketmovie.R
import com.ramanda.ticketmovie.model.Plays

class PlaysAdapter(private var data: List<Plays>,
                   private val listener: (Plays) -> Unit)
    : RecyclerView.Adapter<PlaysAdapter.LeagueViewHolder>() {
    class LeagueViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tv_title)
        private val tvImage: ImageView = view.findViewById(R.id.iv_poster_image)

        fun bindItem(data: Plays, listener: (Plays) -> Unit, context: Context) {
            tvTitle.text = data.nama
            Glide.with(context)
                .load(data.url)
                .apply(RequestOptions.circleCropTransform())
                .into(tvImage)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

    private lateinit var contextAdapter: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.plays_item, parent, false)
        return LeagueViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int {
        return data.size
    }


}
