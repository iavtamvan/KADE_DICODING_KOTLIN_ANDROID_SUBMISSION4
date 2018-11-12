package com.iav.kade.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iav.kade.Adapter.FavoriteAdapter
import com.iav.kade.Helper.Favorite
import com.iav.kade.Helper.database

import com.iav.kade.R
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteFragment : Fragment() {
    private var favorites: ArrayList<Favorite> = arrayListOf()
    private lateinit var adapter: FavoriteAdapter
    private lateinit var listFavoriteku : RecyclerView
    private lateinit var swipe : SwipeRefreshLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_favorite, container, false)
        listFavoriteku = view.findViewById<RecyclerView>(R.id.rv)
        swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)

        getFavorite()

        return view
    }

    private fun getFavorite(){
        swipe.onRefresh {
            swipe.isRefreshing = true
            favorites.clear()
            getFavorite()
        }
        context?.database?.use {
            swipe.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.clear()
            favorites.addAll(favorite)
            if (favorite.size != 0){
                listFavoriteku.layoutManager = LinearLayoutManager(ctx)
                adapter = FavoriteAdapter(activity,favorites)
                listFavoriteku.adapter = adapter
                adapter.notifyDataSetChanged()
            }else{
                toast("data kosong")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getFavorite()
    }


}
