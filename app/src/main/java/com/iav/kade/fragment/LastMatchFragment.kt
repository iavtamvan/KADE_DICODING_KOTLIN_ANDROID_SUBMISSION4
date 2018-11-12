package com.iav.kade.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.cia.footballschedule.utils.invisible
import com.example.cia.footballschedule.utils.visible
import com.iav.kade.R
import com.iav.kade.adapter.LastMatchAdapter
import com.iav.kade.fragment.main.LastMatchPresenter
import com.iav.kade.fragment.main.MainView
import com.iav.kade.model.Item
import kotlinx.android.synthetic.main.fragment_last_match.*

/**
 * A simple [Fragment] subclass.
 *
 */
class LastMatchFragment : Fragment(), MainView {

    private var items: ArrayList<Item> = arrayListOf()
    private lateinit var mAdapter: LastMatchAdapter
    private lateinit var rv: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: LastMatchPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_last_match, container, false)
        rv = view.findViewById(R.id.rv)
        progressBar = view.findViewById(R.id.progress_circular)
        mAdapter = LastMatchAdapter(activity, items)
        presenter = LastMatchPresenter(items, activity, rv, mAdapter)
        presenter.getLastMatch()
        return view
    }

    override fun progressShow() {
        progress_circular.visible()
    }

    override fun progessHide() {
        progress_circular.invisible()
    }
}