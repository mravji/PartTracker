package org.sheridan.scrapyard


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.sheridan.scrapyard.database.computer_part.Parts
import org.sheridan.scrapyard.database.history.History
import org.sheridan.scrapyard.datamodel.DBModel
import org.sheridan.scrapyard.viewpager.PartsViewPagerAdapter
import java.util.*

class MainActivity : AppCompatActivity(), Observer {

    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DBModel.addObserver(this)

        viewPager2 = findViewById(R.id.viewPager)

        val list : ArrayList<ArrayList<Parts>> = ArrayList()
        list.add(DBModel.getGPU()!!)
        list.add(DBModel.getCPU()!!)
        list.add(DBModel.getCCase()!!)
        list.add(DBModel.getHDD()!!)
        list.add(DBModel.getSSD()!!)
        list.add(DBModel.getMOBO()!!)
        list.add(DBModel.getPSU()!!)
        list.add(DBModel.getRAM()!!)

        val priceList : ArrayList<History> = DBModel.getHistory()!!

        val viewPagerAdapter = PartsViewPagerAdapter(this, list, priceList)
        viewPager2.adapter = viewPagerAdapter

        val tabLayout : TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "GPU"
                1 -> tab.text = "CPU"
                2 -> tab.text = "Computer Cases"
                3 -> tab.text = "HDD"
                4 -> tab.text = "SSD"
                5 -> tab.text = "MOBO"
                6 -> tab.text = "PSU"
                else -> tab.text = "RAM"
            }
        }.attach()


    }

    override fun update(p0: Observable?, p1: Any?) {

        val list : ArrayList<ArrayList<Parts>> = ArrayList()
        list.add(DBModel.getGPU()!!)
        list.add(DBModel.getCPU()!!)
        list.add(DBModel.getCCase()!!)
        list.add(DBModel.getHDD()!!)
        list.add(DBModel.getSSD()!!)
        list.add(DBModel.getMOBO()!!)
        list.add(DBModel.getPSU()!!)
        list.add(DBModel.getRAM()!!)

        val priceList : ArrayList<History> = DBModel.getHistory()!!

        val viewPagerAdapter = PartsViewPagerAdapter(this, list, priceList)
        viewPager2.adapter = viewPagerAdapter

        val tabLayout : TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "GPU"
                1 -> tab.text = "CPU"
                2 -> tab.text = "Computer Cases"
                3 -> tab.text = "HDD"
                4 -> tab.text = "SSD"
                5 -> tab.text = "MOBO"
                6 -> tab.text = "PSU"
                else -> tab.text = "RAM"
            }
        }.attach()
    }

    override fun onResume() {
        super.onResume()
        DBModel.addObserver(this)
    }

    override fun onPause() {
        super.onPause()
        DBModel.deleteObserver(this)
    }

    override fun onStop() {
        super.onStop()
        DBModel.deleteObserver(this)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if(id == R.id.item_preferences){

            goToPreferencesActivity()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun goToPreferencesActivity(){
        startActivity(Intent(this, PreferencesActivity::class.java))
    }
}