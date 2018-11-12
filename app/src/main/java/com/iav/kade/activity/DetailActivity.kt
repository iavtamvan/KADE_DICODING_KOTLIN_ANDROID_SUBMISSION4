package com.iav.kade.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.iav.kade.R
import com.iav.kade.R.id.add_to_favorite
import com.iav.kade.activity.main.DetailPresenter
import com.iav.kade.helper.Favorite
import com.iav.kade.helper.database
import com.iav.kade.model.Item
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import java.util.*

class DetailActivity : AppCompatActivity() {
    private var items: MutableList<Item> = mutableListOf()
    private var list: ArrayList<Item> = arrayListOf()
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

        list = intent.getParcelableArrayListExtra("list")
        posisi = intent.getStringExtra("posisi").toInt()
        detailPresenter = DetailPresenter(this,list, posisi, nilai,menuItem, items)
        tv_detail_team_home.text = list.get(posisi).teamHome
        tv_detail_team_away.text = list.get(posisi).teamAway
        tv_detail_score_home.text = list.get(posisi).scoreHome
        tv_detail_score_away.text = list.get(posisi).scoreAway
        tv_formasi_home.text = list.get(posisi).homeFormation
        tv_formasi_away.text = list.get(posisi).awayFormation
        tv_goal_home.text = list.get(posisi).goalHome
        tv_goal_away.text = list.get(posisi).goalAway
        tv_shots_home.text = list.get(posisi).shotsHome
        tv_shots_away.text = list.get(posisi).shotsAway
        tv_keeper_home.text = list.get(posisi).keperHome
        tv_keeper_away.text = list.get(posisi).keperAway
        tv_defend_home.text = list.get(posisi).defenderHome
        tv_defend_away.text = list.get(posisi).defenderAway
        tv_midle_home.text = list.get(posisi).midleHome
        tv_midle_away.text = list.get(posisi).midleAway
        tv_forward_home.text = list.get(posisi).forwardHome
        tv_forward_away.text = list.get(posisi).forwardAway
        tv_detail_tanggal.text = list.get(posisi).dateEvent
        detailPresenter.getHomeTeam(img_home)
        detailPresenter.getawayTeam(img_away)
//        getHomeTeam()
//        getawayTeam()
    }

//    private fun getHomeTeam() {
//        val service: ApiService = RetroConfig.provideApi()
//        service.getTeam("" + list.get(posisi).idHomeTeam)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        { result ->
//                            items.clear()
//                            items = result.teams as MutableList<Item>
//                            val images = (items.get(0).teamBadge)
//
//                            Glide.with(applicationContext)
//                                    .load(images)
//                                    .into(img_home)
//
//                        },
//                        { error ->
//                            toast("" + error.message)
//                        }
//                )
//    }
//
//    private fun getawayTeam() {
//        val service: ApiService = RetroConfig.provideApi()
//        service.getTeam("" + list.get(posisi).idAwayTeam)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        { result ->
//                            items.clear()
//                            items = result.teams as MutableList<Item>
//                            val images = (items.get(0).teamBadge)
//
//                            Glide.with(applicationContext)
//                                    .load(images)
//                                    .into(img_away)
//
//                        },
//                        { error ->
//                            toast("" + error.message)
//                        }
//                )
//    }

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
//                    removeFromFavorite()
//                    setFavorite(nilai)
                    true
                }
                else {
                    detailPresenter.addToFavorite()
                    detailPresenter.setFavorite(nilai)

//                    addToFavorite()
//                    setFavorite(nilai)
                    true
                }
//                isFavorite = !isFavorite
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

//    private fun addToFavorite(){
//        try {
//            database.use {
//                insert(Favorite.TABLE_FAVORITE,
//                        Favorite.LAGA_ID to list.get(posisi).lagaId,
//                        Favorite.TEAM_HOME to list.get(posisi).teamHome,
//                        Favorite.TEAM_AWAY to list.get(posisi).teamAway,
//                        Favorite.SCORE_HOME to list.get(posisi).scoreHome,
//                        Favorite.SCORE_AWAY to list.get(posisi).scoreAway,
//                        Favorite.FHOME to list.get(posisi).homeFormation,
//                        Favorite.FAWAY to list.get(posisi).awayFormation,
//                        Favorite.GOALHOME to list.get(posisi).goalHome,
//                        Favorite.GOALAWAY to list.get(posisi).goalAway,
//                        Favorite.SHOTHOME to list.get(posisi).shotsHome,
//                        Favorite.SHOTAWAY to list.get(posisi).shotsAway,
//                        Favorite.KIPERHOME to list.get(posisi).keperHome,
//                        Favorite.KIPER_AWAY to list.get(posisi).keperAway,
//                        Favorite.DEFENDHOME to list.get(posisi).defenderHome,
//                        Favorite.DEFEND_AWAY to list.get(posisi).defenderAway,
//                        Favorite.MIDLEHOME to list.get(posisi).midleHome,
//                        Favorite.MIDLE_AWAY to list.get(posisi).midleAway,
//                        Favorite.FORWARDHOME to list.get(posisi).forwardHome,
//                        Favorite.FORWARDAWAY to list.get(posisi).forwardAway,
//                        Favorite.IDHOME to list.get(posisi).idHomeTeam,
//                        Favorite.IDAWAY to list.get(posisi).idAwayTeam,
//                        Favorite.TANGGAL to list.get(posisi).dateEvent,
//                        Favorite.TEAM_BADGE to "team")
//            }
//            toast("added to favorite")
//
//            nilai = "favorit"
//
//        } catch (e: SQLiteConstraintException){
//            toast(""+e.localizedMessage)
//
//        }
//    }
//    private fun removeFromFavorite(){
//        try {
//            database.use {
//                delete(Favorite.TABLE_FAVORITE, "(LAGA_ID = {id})",
//                        "id" to list.get(posisi).lagaId.toString())
//            }
//            toast("removed to favorite")
//
//            nilai = "not"
//        } catch (e: SQLiteConstraintException){
//            toast(""+e.localizedMessage)
//        }
//    }
//    private fun setFavorite(pilihan:String) {
//        if (pilihan.equals("not")) {
//            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_star_border_black_24dp)
//        } else if (pilihan.equals("favorit")) {
//            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_star_black_24dp)
//        }
//    }

//    private fun favoriteState(){
//        list = intent.getParcelableArrayListExtra("list")
//        database.use {
//            val result = select(Favorite.TABLE_FAVORITE)
//                    .whereArgs("(LAGA_ID = {id})",
//                            "id" to intent.getStringExtra("id"))
//            val favorite = result.parseList(classParser<Favorite>())
//            if (favorite.size != 0){
//                nilai = "favorit"
//
//                setFavorite(nilai)
//            }else{
//                nilai = "not"
//
//                setFavorite(nilai)
//
//            }
//        }
//    }

}
