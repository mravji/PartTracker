package org.sheridan.scrapyard.database.computer_part

import android.util.Log
import com.google.firebase.database.DataSnapshot

class Parts (snapshot: DataSnapshot) {

    lateinit var name: String
    lateinit var model: String
    lateinit var image: String
    lateinit var https: String

    init{
        try {
            val data: HashMap<String, Any> = snapshot.value as HashMap<String, Any>

            model = snapshot.key ?: ""
            name = data["name"] as String
            image = data["image"] as String
            https = data["https"] as String


        } catch (e:Exception) {
            Log.i("ScrapyardDB", "An error has occurred: \n $e")
        }
    }
}