package com.iav.kade.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.iav.kade.R
import com.iav.kade.R.drawable.ic_star_black_24dp
import com.iav.kade.R.drawable.ic_star_border_black_24dp
import com.iav.kade.R.id.add_to_favorite
import com.iav.kade.activity.main.DetailPresenter
import com.iav.kade.helper.Favorite
import com.iav.kade.helper.database
import com.iav.kade.model.Item
import com.iav.kade.rest.ApiService
import com.iav.kade.rest.RetroConfig
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class DetailActivity : AppCompatActivity() {
    private var items: MutableList<Item> = mutableListOf()
    private var listItem: ArrayList<Item> = arrayListOf()
    private var menuItem: Menu? = null
    private var nilai: String = "not"
    var posisi = 0
    private lateinit var detailPresenter:DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getDataParcel()
    }

    private fun getDataParcel() {
        listItem = intent.getParcelableArrayListExtra("list")
        posisi = intent.getStringExtra("posisi").toInt()
//        favoriteState()
        detailPresenter = DetailPresenter(applicationContext, listItem, posisi, nilai, menuItem, items)
        detailPresenter.favoriteState(intent)
        tv_detail_team_home.text = listItem.get(posisi).teamHome
        tv_detail_team_away.text = listItem.get(posisi).teamAway
        tv_detail_score_home.text = listItem.get(posisi).scoreHome
        tv_detail_score_away.text = listItem.get(posisi).scoreAway
        tv_formasi_home.text = listItem.get(posisi).homeFormation
        tv_formasi_away.text = listItem.get(posisi).awayFormation
        tv_goal_home.text = listItem.get(posisi).goalHome
        tv_goal_away.text = listItem.get(posisi).goalAway
        tv_shots_home.text = listItem.get(posisi).shotsHome
        tv_shots_away.text = listItem.get(posisi).shotsAway
        tv_keeper_home.text = listItem.get(posisi).keperHome
        tv_keeper_away.text = listItem.get(posisi).keperAway
        tv_defend_home.text = listItem.get(posisi).defenderHome
        tv_defend_away.text = listItem.get(posisi).defenderAway
        tv_midle_home.text = listItem.get(posisi).midleHome
        tv_midle_away.text = listItem.get(posisi).midleAway
        tv_forward_home.text = listItem.get(posisi).forwardHome
        tv_forward_away.text = listItem.get(posisi).forwardAway
        tv_detail_tanggal.text = listItem.get(posisi).dateEvent
        detailPresenter.getHomeTeam(img_home)
        detailPresenter.getawayTeam(img_away)
    }

    private fun getHomeTeam() {
        val service: ApiService = RetroConfig.provideApi()
        service.getTeam("" + listItem.get(posisi).idHomeTeam)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            items.clear()
                            items = result.teams as MutableList<Item>
                            val images = (items.get(0).teamBadge)

                            Glide.with(applicationContext)
                                    .load(images)
                                    .into(img_home)

                        },
                        { error ->
                            toast("" + error.message)
                        }
                )
    }

    private fun getawayTeam() {
        val service: ApiService = RetroConfig.provideApi()
        service.getTeam("" + listItem.get(posisi).idAwayTeam)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            items.clear()
                            items = result.teams as MutableList<Item>
                            val images = (items.get(0).teamBadge)

                            Glide.with(applicationContext)
                                    .load(images)
                                    .into(img_away)

                        },
                        { error ->
                            toast("" + error.message)
                        }
                )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        menuItem = menu
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (nilai.equals("favorit")){
                    detailPresenter.removeFromFavorite()
                    detailPresenter.setFavorite(nilai)
                    true
                }
                else {
                    detailPresenter.addToFavorite()
                    detailPresenter.setFavorite(nilai)
                    true
                }
//                isFavorite = !isFavorite
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.LAGA_ID to listItem.get(posisi).lagaId,
                        Favorite.TEAM_HOME to listItem.get(posisi).teamHome,
                        Favorite.TEAM_AWAY to listItem.get(posisi).teamAway,
                        Favorite.SCORE_HOME to listItem.get(posisi).scoreHome,
                        Favorite.SCORE_AWAY to listItem.get(posisi).scoreAway,
                        Favorite.FHOME to listItem.get(posisi).homeFormation,
                        Favorite.FAWAY to listItem.get(posisi).awayFormation,
                        Favorite.GOALHOME to listItem.get(posisi).goalHome,
                        Favorite.GOALAWAY to listItem.get(posisi).goalAway,
                        Favorite.SHOTHOME to listItem.get(posisi).shotsHome,
                        Favorite.SHOTAWAY to listItem.get(posisi).shotsAway,
                        Favorite.KIPERHOME to listItem.get(posisi).keperHome,
                        Favorite.KIPER_AWAY to listItem.get(posisi).keperAway,
                        Favorite.DEFENDHOME to listItem.get(posisi).defenderHome,
                        Favorite.DEFEND_AWAY to listItem.get(posisi).defenderAway,
                        Favorite.MIDLEHOME to listItem.get(posisi).midleHome,
                        Favorite.MIDLE_AWAY to listItem.get(posisi).midleAway,
                        Favorite.FORWARDHOME to listItem.get(posisi).forwardHome,
                        Favorite.FORWARDAWAY to listItem.get(posisi).forwardAway,
                        Favorite.IDHOME to listItem.get(posisi).idHomeTeam,
                        Favorite.IDAWAY to listItem.get(posisi).idAwayTeam,
                        Favorite.TANGGAL to listItem.get(posisi).dateEvent,
                        Favorite.TEAM_BADGE to "team")
            }
            toast("added to favorite")

            nilai = "favorit"

        } catch (e: SQLiteConstraintException) {
            toast("" + e.localizedMessage)

        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(LAGA_ID = {id})",
                        "id" to listItem.get(posisi).lagaId.toString())
            }
            toast("removed to favorite")

            nilai = "not"
        } catch (e: SQLiteConstraintException) {
            toast("" + e.localizedMessage)
        }
    }

    private fun setFavorite(pilihan: String) {
        if (pilihan.equals("not")) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_star_border_black_24dp)
        } else if (pilihan.equals("favorit")) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_star_black_24dp)
        }
    }

    private fun favoriteState() {
        listItem = intent.getParcelableArrayListExtra("listItem")
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(LAGA_ID = {id})",
                            "id" to intent.getStringExtra("id"))
            val favorite = result.parseList(classParser<Favorite>())
            if (favorite.size != 0) {
                nilai = "favorit"

                setFavorite(nilai)
            } else {
                nilai = "not"

                setFavorite(nilai)

            }
        }
    }
}
