package com.iav.kade.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iav.kade.Activity.DetailFavActivity
import com.iav.kade.Helper.Favorite
import com.iav.kade.R
import kotlinx.android.synthetic.main.list_last_match.view.*

class FavoriteAdapter(private val context: Context?, private val items: ArrayList<Favorite>)
    : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tanggal.text = items.get(position).tanggal
        holder.homeTeam.text = items.get(position).teamHome
        holder.awayTeam.text = items.get(position).teamAway
        holder.s_home.text = items.get(position).scoreHome
        holder.s_away.text = items.get(position).scoreAway

        holder.itemView.setOnClickListener({
            val intent = Intent(context, DetailFavActivity::class.java)
            intent.putParcelableArrayListExtra("list", items)
            intent.putExtra("posisi", holder.adapterPosition.toString())
            intent.putExtra("id", "${items.get(position).lagaId}")
            context?.startActivity(intent)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_last_match, parent, false))

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tanggal = view.tv_tanggal
        val homeTeam = view.tv_team_home
        val awayTeam = view.tv_team_away
        val s_home = view.tv_score_home
        val s_away = view.tv_score_away
    }
}