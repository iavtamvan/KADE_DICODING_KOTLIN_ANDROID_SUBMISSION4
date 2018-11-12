package com.iav.kade.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iav.kade.Activity.DetailActivity
import com.iav.kade.Model.Item
import com.iav.kade.R
import kotlinx.android.synthetic.main.list_next_match.view.*


class NextMatchAdapter : RecyclerView.Adapter<NextMatchAdapter.ViewHolder> {

    private lateinit var context: Context
    private var items: ArrayList<Item>? = null
    private var orig: ArrayList<Item>? = null
//    private var recycleFilter: RecycleFilter? = null

    constructor(context: Context?, items: ArrayList<Item>) : this() {
        this.context = context!!
        this.items = items
        this.orig = items
    }

    constructor()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_next_match, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tanggal.text = items?.get(position)?.dateEvent
        holder.tv_event.text = items?.get(position)?.event
//        holder.s_home.text = items?.get(position)?.scoreHome
//        holder.s_away.text = items?.get(position)?.scoreAway

        holder.itemView.setOnClickListener({
            val intent = Intent(context, DetailActivity::class.java)
            intent.putParcelableArrayListExtra("list", items)
            intent.putExtra("posisi", holder.adapterPosition.toString())
            intent.putExtra("id", "${items?.get(position)?.lagaId}")
            context?.startActivity(intent)
        })
    }

    override fun getItemCount(): Int = items?.size as Int

//    override fun getFilter(): Filter {
//        if (recycleFilter == null) {
//            recycleFilter = RecycleFilter()
//        }
//        return recycleFilter as RecycleFilter
//    }

//    inner class RecycleFilter : Filter() {
//        override fun performFiltering(charSequence: CharSequence?): FilterResults {
//            var results2: FilterResults = FilterResults()
//            if (charSequence != null && charSequence.length > 0) {
//                var locallist: ArrayList<Item> = ArrayList<Item>()
//                for (i: Int in 0..orig?.size?.minus(1) as Int) {
//                    if (orig?.get(i)?.teamHome?.toLowerCase()?.contains(charSequence.toString().toLowerCase()) as Boolean) {
//                        locallist.add(orig?.get(i) as Item)
//                    }
//                }
//                results2.values = locallist
//                results2.count = locallist.size as Int
//            } else {
//                results2.values = orig
//                results2.count = orig?.size as Int
//            }
//
//            return results2
//        }
//
//        override fun publishResults(charSequence: CharSequence, filterResults: FilterResults?) {
//            items = filterResults?.values as ArrayList<Item>
//            notifyDataSetChanged()
//        }
//
//    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tanggal = view.tv_tanggal_next
        val tv_event = view.tv_event
    }
}