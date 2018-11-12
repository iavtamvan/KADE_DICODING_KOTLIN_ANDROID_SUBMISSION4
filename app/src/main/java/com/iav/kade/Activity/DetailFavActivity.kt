package com.iav.kade.Activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.iav.kade.Helper.Favorite
import com.iav.kade.Helper.database
import com.iav.kade.Model.Item
import com.iav.kade.R
import com.iav.kade.Rest.ApiService
import com.iav.kade.Rest.RetroConfig
import kotlinx.android.synthetic.main.activity_detail_fav.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class DetailFavActivity : AppCompatActivity() {
    private var items: MutableList<Item> = mutableListOf()
    private var list: ArrayList<Favorite> = arrayListOf()
    private var menuItem: Menu? = null
    private var nilai: String = "not"
    var posisi = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_fav)
        ambilData()

    }

    private fun ambilData() {
        list = intent.getParcelableArrayListExtra("list")
        favoriteState()
        posisi = intent.getStringExtra("posisi").toInt()
        tv_detail_team_home.text = list.get(posisi).teamHome
        tv_detail_team_away.text = list.get(posisi).teamAway
        tv_detail_score_home.text = list.get(posisi).scoreHome
        tv_detail_score_away.text = list.get(posisi).scoreAway
        tv_formasi_home.text = list.get(posisi).fHome
        tv_formasi_away.text = list.get(posisi).fAway
        tv_goal_home.text = list.get(posisi).goalHome
        tv_goal_away.text = list.get(posisi).goalAway
        tv_shots_home.text = list.get(posisi).shotHome
        tv_shots_away.text = list.get(posisi).shotAway
        tv_keeper_home.text = list.get(posisi).kiperHome
        tv_keeper_away.text = list.get(posisi).kiperAway
        tv_defend_home.text = list.get(posisi).defendHome
        tv_defend_away.text = list.get(posisi).defendAway
        tv_midle_home.text = list.get(posisi).midleHome
        tv_midle_away.text = list.get(posisi).midleAway
        tv_forward_home.text = list.get(posisi).forwardHome
        tv_forward_away.text = list.get(posisi).forwardAway
        tv_detail_tanggal.text = list.get(posisi).tanggal

        getHome()
        getaway()
    }

    private fun getHome() {
        val service: ApiService = RetroConfig.provideApi()
        service.getTeam("" + list.get(posisi).idHome)
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

    private fun getaway() {
        val service: ApiService = RetroConfig.provideApi()
        service.getTeam("" + list.get(posisi).idAway)
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
        menuInflater.inflate(R.menu.menu_fav, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (nilai.equals("favorit")) {
                    removeFromFavorite()
                    setFavorite()
                    true
                } else {
                    addToFavorite()
                    setFavorite()
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
                        Favorite.LAGA_ID to list.get(posisi).lagaId,
                        Favorite.TEAM_HOME to list.get(posisi).teamHome,
                        Favorite.TEAM_AWAY to list.get(posisi).teamAway,
                        Favorite.SCORE_HOME to list.get(posisi).scoreHome,
                        Favorite.SCORE_AWAY to list.get(posisi).scoreAway,
                        Favorite.FHOME to list.get(posisi).fHome,
                        Favorite.FAWAY to list.get(posisi).fAway,
                        Favorite.GOALHOME to list.get(posisi).goalHome,
                        Favorite.GOALAWAY to list.get(posisi).goalAway,
                        Favorite.SHOTHOME to list.get(posisi).shotHome,
                        Favorite.SHOTAWAY to list.get(posisi).shotAway,
                        Favorite.KIPERHOME to list.get(posisi).kiperHome,
                        Favorite.KIPER_AWAY to list.get(posisi).kiperAway,
                        Favorite.DEFENDHOME to list.get(posisi).defendHome,
                        Favorite.DEFEND_AWAY to list.get(posisi).defendAway,
                        Favorite.MIDLEHOME to list.get(posisi).midleHome,
                        Favorite.MIDLE_AWAY to list.get(posisi).midleAway,
                        Favorite.FORWARDHOME to list.get(posisi).forwardHome,
                        Favorite.FORWARDAWAY to list.get(posisi).forwardAway,
                        Favorite.IDHOME to list.get(posisi).idHome,
                        Favorite.IDAWAY to list.get(posisi).idAway,
                        Favorite.TANGGAL to list.get(posisi).tanggal,
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
                        "id" to list.get(posisi).lagaId.toString())
            }
            toast("removed to favorite")
            ambilData()

            nilai = "not"

        } catch (e: SQLiteConstraintException) {
            toast("" + e.localizedMessage)

        }
    }

    private fun setFavorite() {
        if (nilai.equals("not"))
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_black_24dp)
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(LAGA_ID = {id})",
                            "id" to intent.getStringExtra("id"))
            val favorite = result.parseList(classParser<Favorite>())
            if (favorite.size != 0) {
                nilai = "favorit"
                setFavorite()
            } else {
                nilai = "not"
                setFavorite()
            }
        }
    }
}
