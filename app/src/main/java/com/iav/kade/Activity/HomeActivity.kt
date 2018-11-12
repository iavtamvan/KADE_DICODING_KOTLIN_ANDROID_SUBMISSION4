package com.iav.kade.Activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.iav.kade.Fragment.FavoriteFragment
import com.iav.kade.Fragment.LastMatchFragment
import com.iav.kade.Fragment.NextMatchFragment
import com.iav.kade.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.last_match -> {
                lastMatch()
                return@OnNavigationItemSelectedListener true
            }
            R.id.next_match -> {
                nextMatch()
                return@OnNavigationItemSelectedListener true
            }
            R.id.fav -> {
                fav()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun fav() {
        val transaction = fragmentManager.beginTransaction()
        val fragment = FavoriteFragment()
        transaction.replace(R.id.frame,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun lastMatch() {

        val transaction = fragmentManager.beginTransaction()
        val fragment = LastMatchFragment()
        transaction.replace(R.id.frame,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun nextMatch() {

        val transaction = fragmentManager.beginTransaction()
        val fragment = NextMatchFragment()
        transaction.replace(R.id.frame,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        lastMatch()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
