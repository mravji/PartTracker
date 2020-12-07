package org.sheridan.scrapyard.viewpager.recyclerview

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_parts.view.*
import org.sheridan.scrapyard.PriceHistory
import org.sheridan.scrapyard.R
import org.sheridan.scrapyard.database.computer_part.Parts
import org.sheridan.scrapyard.database.history.History

class PartsRecyclerViewAdapter(private val list: ArrayList<Parts>,
                               private val priceList: ArrayList<History>,
                               private val context: Context
): RecyclerView.Adapter<PartsRecyclerViewAdapter.PartsViewHolder>() {

    class PartsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val partName: TextView = itemView.txtname
        val model: TextView = itemView.txtmodel
        val partImage: ImageView = itemView.partImage
        val partLink: Button = itemView.browserButton
        val partPrice: TextView = itemView.txtPrice
        val priceButton: Button = itemView.priceButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_parts, parent, false)

        return PartsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PartsViewHolder, position: Int) {

        var map: HashMap<String, String>
        val currentPart = list[position]
        holder.partPrice.text = "No price available"
        for (i in 0 until priceList.size) {
            if (currentPart.model == priceList[i].model) {
                map = priceList[i].histogram
                val date  = map.keys

                var sortedMap = map.toList().sortedBy { (key) -> key }.toMap()
                val sortedDate = sortedMap.keys
                var price = "$ ${sortedMap[(sortedDate.elementAt(sortedDate.size-1 ))]}"

                if (price == "$ ") {
                    price = "None in stock"
                }
                holder.partPrice.text = price
                break
            }
        }
        holder.partName.text = currentPart.name
        holder.model.text = currentPart.model
        Picasso.get().load(currentPart.image).into(holder.partImage)
        holder.partLink.setOnClickListener {
            val uri : Uri = Uri.parse(currentPart.https)
            val launchBrowser : Intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(context,launchBrowser, Bundle())
        }
        holder.priceButton.setOnClickListener {
//            var bundle = Bundle()
//            bundle.putString("model", currentPart.model)
//            bundle.putString("image", currentPart.image)
            val priceHist : Intent = Intent(context, PriceHistory::class.java).apply{
                putExtra("model", currentPart.model)
                putExtra("image", currentPart.image)
            }
            startActivity(context, priceHist, Bundle())
        }

    }

    override fun getItemCount(): Int = list.size


}