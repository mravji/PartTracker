package org.sheridan.scrapyard.datamodel

import android.util.Log
import com.google.firebase.database.*
import org.sheridan.scrapyard.database.computer_part.Parts
import org.sheridan.scrapyard.database.history.History
import java.util.*
import kotlin.collections.ArrayList

object DBModel: Observable() {

    private const val TAG = "Scrapyard"

    // Listeners for each object in the computer_part object
    private var valueModelListener_CCase: ValueEventListener? = null
    private var valueModelListener_CPU: ValueEventListener? = null
    private var valueModelListener_GPU: ValueEventListener? = null
    private var valueModelListener_HDD: ValueEventListener? = null
    private var valueModelListener_MOBO: ValueEventListener? = null
    private var valueModelListener_PSU: ValueEventListener? = null
    private var valueModelListener_RAM: ValueEventListener? = null
    private var valueModelListener_SSD: ValueEventListener? = null

    // Listeners for each object in the history object
    private var valueModelListener_History: ValueEventListener? = null



    // Array lists for each object in the database
    private var listCCase: ArrayList<Parts>? = ArrayList()
    private var listCPU: ArrayList<Parts>? = ArrayList()
    private var listGPU: ArrayList<Parts>? = ArrayList()
    private var listHDD: ArrayList<Parts>? = ArrayList()
    private var listMobo: ArrayList<Parts>? = ArrayList()
    private var listPSU: ArrayList<Parts>? = ArrayList()
    private var listRAM: ArrayList<Parts>? = ArrayList()
    private var listSSD: ArrayList<Parts>? = ArrayList()
    private var listHistory: ArrayList<History>? = ArrayList()

    // Collection of references to objects in firebase db

    private fun dbRefModelCCase(): DatabaseReference? =
        FirebaseDatabase.getInstance().reference.child("computer_part/computer_case")

    private fun dbRefModelCPU(): DatabaseReference? =
        FirebaseDatabase.getInstance().reference.child("computer_part/cpu")

    private fun dbRefModelGPU(): DatabaseReference? =
        FirebaseDatabase.getInstance().reference.child("computer_part/gpu")

    private fun dbRefModelHDD(): DatabaseReference? =
        FirebaseDatabase.getInstance().reference.child("computer_part/hdd")

    private fun dbRefModelMOBO(): DatabaseReference? =
        FirebaseDatabase.getInstance().reference.child("computer_part/motherboard")

    private fun dbRefModelPSU(): DatabaseReference? =
        FirebaseDatabase.getInstance().reference.child("computer_part/psu")

    private fun dbRefModelRAM(): DatabaseReference? =
        FirebaseDatabase.getInstance().reference.child("computer_part/ram")

    private fun dbRefModelSSD(): DatabaseReference? =
        FirebaseDatabase.getInstance().reference.child("computer_part/ssd")

    private fun dbRefModelHistory(): DatabaseReference? =
        FirebaseDatabase.getInstance().reference.child("history")

    private fun removeEventListeners(listener: ValueEventListener?) {

        if (listener != null) {
            when (listener) {
                valueModelListener_CCase ->
                    dbRefModelCCase()?.removeEventListener(listener!!)
                valueModelListener_CPU ->
                    dbRefModelCPU()?.removeEventListener(listener!!)
                valueModelListener_GPU ->
                    dbRefModelGPU()?.removeEventListener(listener!!)
                valueModelListener_HDD ->
                    dbRefModelHDD()?.removeEventListener(listener!!)
                valueModelListener_MOBO ->
                    dbRefModelMOBO()?.removeEventListener(listener!!)
                valueModelListener_PSU ->
                    dbRefModelPSU()?.removeEventListener(listener!!)
                valueModelListener_RAM ->
                    dbRefModelRAM()?.removeEventListener(listener!!)
                valueModelListener_SSD ->
                    dbRefModelSSD()?.removeEventListener(listener!!)
                valueModelListener_History ->
                    dbRefModelHistory()?.removeEventListener(listener!!)
            }
        }

    }

    private fun setAllNulls() {
        valueModelListener_CCase = null
        valueModelListener_CPU = null
        valueModelListener_GPU = null
        valueModelListener_HDD = null
        valueModelListener_MOBO = null
        valueModelListener_PSU = null
        valueModelListener_RAM = null
        valueModelListener_SSD = null
        valueModelListener_History = null
    }

    init {

        // Remove any event listeners currently attached
        removeEventListeners(valueModelListener_CCase)
        removeEventListeners(valueModelListener_CPU)
        removeEventListeners(valueModelListener_GPU)
        removeEventListeners(valueModelListener_HDD)
        removeEventListeners(valueModelListener_MOBO)
        removeEventListeners(valueModelListener_PSU)
        removeEventListeners(valueModelListener_RAM)
        removeEventListeners(valueModelListener_SSD)
        removeEventListeners(valueModelListener_History)
        setAllNulls()

        // Add event listeners to each object
        valueModelListener_CCase = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {

                    val data: ArrayList<Parts> = ArrayList()

                    for (snapshot: DataSnapshot in dataSnapshot.children) {

                        try {

                            data.add(Parts(snapshot))

                        } catch (e: Exception) {

                            Log.i("$TAG : CCase", "Error: \n $e")

                        }
                    }

                    listCCase = data
                    Log.i("$TAG : CCase", "Data Updated, there are " +
                            "${listCCase!!.size} parts in the cache")
                    setChanged()
                    notifyObservers()
                } catch (e: Exception) {

                    Log.i("$TAG : CCase", "Error: \n $e")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("$TAG : CCase","Error: \n ${error.message}")
            }

        }

        valueModelListener_CPU = object: ValueEventListener {

            override fun onCancelled(error: DatabaseError) {

                Log.i("$TAG : CPart", "Error: \n ${error.message}")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {

                    val data: ArrayList<Parts> = ArrayList()

                    for (snapshot: DataSnapshot in dataSnapshot.children) {

                        try {
                            data.add(Parts(snapshot))

                        } catch (e: Exception) {

                            Log.i("$TAG : CPU", "Error: \n $e")

                        }
                    }

                    listCPU = data
                    Log.i("$TAG : CPU", "Data Updated, there are " +
                            "${listCPU!!.size} parts in the cache")
                    setChanged()
                    notifyObservers()

                } catch (e: Exception){
                    Log.i("$TAG : CPU", "Error: \n $e")
                }
            }
        }

        valueModelListener_GPU = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {

                    val data: ArrayList<Parts> = ArrayList()

                    for (snapshot: DataSnapshot in dataSnapshot.children) {

                        try {

                            data.add(Parts(snapshot))

                        } catch (e: Exception) {

                            Log.i("$TAG : GPU", "Error: \n $e")

                        }
                    }

                    listGPU = data
                    Log.i("$TAG : GPU", "Data Updated, there are " +
                            "${listGPU!!.size} parts in the cache")
                    setChanged()
                    notifyObservers()
                } catch (e: Exception) {

                    Log.i("$TAG : GPU", "Error: \n $e")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("$TAG : GPU","Error: \n ${error.message}")
            }

        }

        valueModelListener_HDD = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {

                    val data: ArrayList<Parts> = ArrayList()

                    for (snapshot: DataSnapshot in dataSnapshot.children) {

                        try {

                            data.add(Parts(snapshot))

                        } catch (e: Exception) {

                            Log.i("$TAG : HDD", "Error: \n $e")

                        }
                    }

                    listHDD = data
                    Log.i("$TAG : HDD", "Data Updated, there are " +
                            "${listHDD!!.size} parts in the cache")
                    setChanged()
                    notifyObservers()
                } catch (e: Exception) {

                    Log.i("$TAG : HDD", "Error: \n $e")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("$TAG : HDD","Error: \n ${error.message}")
            }

        }

        valueModelListener_MOBO = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {

                    val data: ArrayList<Parts> = ArrayList()

                    for (snapshot: DataSnapshot in dataSnapshot.children) {

                        try {

                            data.add(Parts(snapshot))

                        } catch (e: Exception) {

                            Log.i("$TAG : MOBO", "Error: \n $e")

                        }
                    }

                    listMobo = data
                    Log.i("$TAG : MOBO", "Data Updated, there are " +
                            "${listMobo!!.size} parts in the cache")
                    setChanged()
                    notifyObservers()
                } catch (e: Exception) {

                    Log.i("$TAG : MOBO", "Error: \n $e")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("$TAG : MOBO" ,"Error: \n ${error.message}")
            }

        }

        valueModelListener_PSU = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {

                    val data: ArrayList<Parts> = ArrayList()

                    for (snapshot: DataSnapshot in dataSnapshot.children) {

                        try {

                            data.add(Parts(snapshot))

                        } catch (e: Exception) {

                            Log.i("$TAG : PSU", "Error: \n $e")

                        }
                    }

                    listPSU = data
                    Log.i("$TAG : PSU", "Data Updated, there are " +
                            "${listPSU!!.size} parts in the cache")
                    setChanged()
                    notifyObservers()
                } catch (e: Exception) {

                    Log.i("$TAG : PSU", "Error: \n $e")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("$TAG : PSU","Error: \n ${error.message}")
            }

        }

        valueModelListener_RAM = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {

                    val data: ArrayList<Parts> = ArrayList()

                    for (snapshot: DataSnapshot in dataSnapshot.children) {

                        try {

                            data.add(Parts(snapshot))

                        } catch (e: Exception) {

                            Log.i("$TAG : RAM", "Error: \n $e")

                        }
                    }

                    listRAM = data
                    Log.i("$TAG : RAM", "Data Updated, there are " +
                            "${listRAM!!.size} parts in the cache")
                    setChanged()
                    notifyObservers()
                } catch (e: Exception) {

                    Log.i("$TAG : RAM", "Error: \n $e")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("$TAG : RAM","Error: \n ${error.message}")
            }

        }

        valueModelListener_SSD = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {

                    val data: ArrayList<Parts> = ArrayList()

                    for (snapshot: DataSnapshot in dataSnapshot.children) {

                        try {

                            data.add(Parts(snapshot))

                        } catch (e: Exception) {

                            Log.i("$TAG : SSD", "Error: \n $e")

                        }
                    }

                    listSSD = data
                    Log.i("$TAG : SSD", "Data Updated, there are " +
                            "${listSSD!!.size} parts in the cache")
                    setChanged()
                    notifyObservers()
                } catch (e: Exception) {

                    Log.i("$TAG : SSD", "Error: \n $e")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("$TAG : SSD","Error: \n ${error.message}")
            }

        }

        valueModelListener_History = object: ValueEventListener {

            override fun onDataChange(dbSnapshot: DataSnapshot) {

                try {

                    val data :ArrayList<History> = ArrayList()

                    for (snapshot: DataSnapshot in dbSnapshot.children) {

                        try {

                            data.add(History(snapshot))

                        } catch (e: Exception) {

                            Log.i("$TAG : Hist" , "Error: \n $e")
                        }

                    }

                    listHistory = data
                    Log.i("$TAG : Hist", "Data Updated, there are " +
                            "${listHistory!!.size} parts in the cache")
                    setChanged()
                    notifyObservers()

                } catch (e: Exception) {
                    Log.i(TAG, "Error: \n $e")
                }


            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("$TAG : Hist", "Error: \n ${error.message}")
            }
        }

        // Lambda expression to add an event listener to the computer_part object


        /*
            Assign listeners to the reference models
            Loads information from database
         */
        dbRefModelCCase()?.addValueEventListener(valueModelListener_CCase!!)
        dbRefModelCPU()?.addValueEventListener(valueModelListener_CPU!!)
        dbRefModelGPU()?.addValueEventListener(valueModelListener_GPU!!)
        dbRefModelHDD()?.addValueEventListener(valueModelListener_HDD!!)
        dbRefModelMOBO()?.addValueEventListener(valueModelListener_MOBO!!)
        dbRefModelPSU()?.addValueEventListener(valueModelListener_PSU!!)
        dbRefModelRAM()?.addValueEventListener(valueModelListener_RAM!!)
        dbRefModelSSD()?.addValueEventListener(valueModelListener_SSD!!)

        dbRefModelHistory()?.addValueEventListener(valueModelListener_History!!)
    }

    fun getCCase(): ArrayList<Parts>? = listCCase
    fun getCPU(): ArrayList<Parts>? = listCPU
    fun getGPU(): ArrayList<Parts>? = listGPU
    fun getHDD() : ArrayList<Parts>? = listHDD
    fun getMOBO() : ArrayList<Parts>? = listMobo
    fun getPSU() : ArrayList<Parts>? = listPSU
    fun getRAM() : ArrayList<Parts>? = listRAM
    fun getSSD() : ArrayList<Parts>? = listSSD

    fun getHistory(): ArrayList<History>? =  listHistory



}