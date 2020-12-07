package org.sheridan.scrapyard

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginLeft
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.price_history.*
import org.sheridan.scrapyard.datamodel.DBModel
import java.util.*
import kotlin.collections.HashMap

class PriceHistory: AppCompatActivity(), Observer {

	private lateinit var model: String
	private lateinit var image : String

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.price_history)
		DBModel.addObserver(this)
		model = intent.getStringExtra("model").toString()
		image = intent.getStringExtra("image").toString()

		txtpartPrice.text = model
		Picasso.get().load(image).into(partPriceImage)

		modifiedUpdate()


	}


	private fun modifiedUpdate() {

		val table : TableLayout = findViewById(R.id.table)

		val list = DBModel.getHistory()!!

		var map: HashMap<String, String>

        var sortedMap: Map<String, String>

		for (i in 0 until list.size) {

			if (model == list[i].model) {

                map = list[i].histogram

                sortedMap = map.toList().sortedBy { (key) -> key }.toMap()

                for (i in 0 until sortedMap.size) {

                    var tableRow = TableRow(this)
                    var rowDate = TextView(this)
                    var rowPrice = TextView(this)

                    rowDate.textSize = 25f
                    rowPrice.textSize = 25f

                    rowDate.setPadding(10,0,30,0)
                    rowPrice.setPadding(10,0,30,0)

                    tableRow.setPadding(130,50,130,50)

                    var sortedDate = sortedMap.keys
                    var price =
                        "$ ${sortedMap[(sortedDate.elementAt(sortedDate.indexOf(sortedDate.elementAt(i))))]}"

                    if (price == "$ ") {
                        price = "None in stock"
                    }
                    rowDate.text = sortedDate.elementAt(i)
                    rowPrice.text = price

                    tableRow.addView(rowDate)
                    tableRow.addView(rowPrice)

                    table.addView(tableRow,i)

                }
            }
		}
	}

	override fun update(p0: Observable?, p1: Any?) {

	   modifiedUpdate()
	}
}