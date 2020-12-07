package org.sheridan.scrapyard.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.parts_list.view.*
import org.sheridan.scrapyard.R
import org.sheridan.scrapyard.database.computer_part.Parts
import org.sheridan.scrapyard.database.history.History
import org.sheridan.scrapyard.viewpager.recyclerview.PartsRecyclerViewAdapter

class PartsViewPagerAdapter(private val context: Context,
                            private val list: ArrayList<ArrayList<Parts>>,
                            private val priceList: ArrayList<History>) :
    RecyclerView.Adapter<PartsViewPagerAdapter.RecyclerViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.parts_list, parent, false)

        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        val currentItem = list[position]
        holder.recyclerView.adapter = PartsRecyclerViewAdapter(currentItem, priceList, context)
        holder.recyclerView.layoutManager = LinearLayoutManager(context)
        holder.recyclerView.setHasFixedSize(true)
    }

    override fun getItemCount(): Int = list!!.size

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val recyclerView : RecyclerView = itemView.recyclerView
    }

}