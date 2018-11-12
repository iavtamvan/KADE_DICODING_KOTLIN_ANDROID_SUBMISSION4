package com.iav.kade.activity.main

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.provider.SyncStateContract.Helpers.insert
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.iav.kade.R.drawable.ic_star_black_24dp
import com.iav.kade.R.drawable.ic_star_border_black_24dp
import com.iav.kade.helper.Favorite
import com.iav.kade.helper.database
import com.iav.kade.model.Item
import com.iav.kade.rest.ApiService
import com.iav.kade.rest.RetroConfig
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.nio.file.Files.delete
import java.util.*

class DetailPresenter(private var context: Context,
                      private var list: ArrayList<Item> = arrayListOf(),
                      var posisi: Int,
                      private var nilai: String ,
                      private var menuItem: Menu?,
                      private var items: MutableList<Item> = mutableListOf()) {

    fun addToFavorite() {
        try {
            context.database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.LAGA_ID to list.get(posisi).lagaId,
                        Favorite.TEAM_HOME to list.get(posisi).teamHome,
                        Favorite.TEAM_AWAY to list.get(posisi).teamAway,
                        Favorite.SCORE_HOME to list.get(posisi).scoreHome,
                        Favorite.SCORE_AWAY to list.get(posisi).scoreAway,
                        Favorite.FHOME to list.get(posisi).homeFormation,
                        Favorite.FAWAY to list.get(posisi).awayFormation,
                        Favorite.GOALHOME to list.get(posisi).goalHome,
                        Favorite.GOALAWAY to list.get(posisi).goalAway,
                        Favorite.SHOTHOME to list.get(posisi).shotsHome,
                        Favorite.SHOTAWAY to list.get(posisi).shotsAway,
                        Favorite.KIPERHOME to list.get(posisi).keperHome,
                        Favorite.KIPER_AWAY to list.get(posisi).keperAway,
                        Favorite.DEFENDHOME to list.get(posisi).defenderHome,
                        Favorite.DEFEND_AWAY to list.get(posisi).defenderAway,
                        Favorite.MIDLEHOME to list.get(posisi).midleHome,
                        Favorite.MIDLE_AWAY to list.get(posisi).midleAway,
                        Favorite.FORWARDHOME to list.get(posisi).forwardHome,
                        Favorite.FORWARDAWAY to list.get(posisi).forwardAway,
                        Favorite.IDHOME to list.get(posisi).idHomeTeam,
                        Favorite.IDAWAY to list.get(posisi).idAwayTeam,
                        Favorite.TANGGAL to list.get(posisi).dateEvent,
                        Favorite.TEAM_BADGE to "team")
            }
            Toast.makeText(context, "Added to favorite", Toast.LENGTH_SHORT).show()

            nilai = "favorit"

        } catch (e: SQLiteConstraintException){
            Toast.makeText(context, "" + e.localizedMessage, Toast.LENGTH_SHORT).show()

        }
    }

    fun removeFromFavorite() {
        try {
            context.database.use {
                delete(Favorite.TABLE_FAVORITE, "(LAGA_ID = {id})",
                        "id" to list.get(posisi).lagaId.toString())
            }
            Toast.makeText(context, "Remove to favorite", Toast.LENGTH_SHORT).show()

            nilai = "not"
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(context, "" + e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    fun setFavorite(pilihan: String) {
        if (pilihan.equals("not")) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(context, ic_star_border_black_24dp)
        } else if (pilihan.equals("favorit")) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(context, ic_star_black_24dp)
        }
    }

    fun getawayTeam(imageView: ImageView) {
        val service: ApiService = RetroConfig.provideApi()
        service.getTeam("" + list.get(posisi).idAwayTeam)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            items.clear()
                            items = result.teams as MutableList<Item>
                            val images = (items.get(0).teamBadge)

                            Glide.with(context)
                                    .load(images)
                                    .into(imageView)

                        },
                        { error ->
                            Toast.makeText(context, "" + error.message, Toast.LENGTH_SHORT).show()
                        }
                )
    }

    fun getHomeTeam(imageView: ImageView) {
        val service: ApiService = RetroConfig.provideApi()
        service.getTeam("" + list.get(posisi).idHomeTeam)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            items.clear()
                            items = result.teams as MutableList<Item>
                            val images = (items.get(0).teamBadge)

                            Glide.with(context)
                                    .load(images)
                                    .into(imageView)

                        },
                        { error ->
                            Toast.makeText(context, "" + error.message, Toast.LENGTH_SHORT).show()
                        }
                )
    }

    fun favoriteState(intent: Intent) {
        list = intent.getParcelableArrayListExtra("list")
        context.database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(LAGA_ID = {id})",
                            "id" to intent.getStringExtra("id"))
            val favorite = result.parseList(classParser<Favorite>())
            if (favorite.size != 0) {
                nilai = "favorit"

//                setFavorite(nilai)
            } else {
                nilai = "not"

//                setFavorite(nilai)

            }
        }
    }
}