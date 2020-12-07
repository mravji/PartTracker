package org.sheridan.scrapyard.database.history

import android.util.Log
import com.google.firebase.database.DataSnapshot

class History (snapshot: DataSnapshot) {

    lateinit var histogram: HashMap<String, String>
    lateinit var model: String

    init{
        try {
            val data: HashMap<String, String> = snapshot.value as HashMap<String, String>

            model = snapshot.key ?: ""
            histogram = data

        } catch (e:Exception) {
            Log.i("ScrapyardDB_History", "An error has occurred: \n ${e.toString()}")
        }
    }
}