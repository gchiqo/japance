package com.example.japance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class FetchingActivity : AppCompatActivity() {

    private lateinit var ThgRecyclerView: RecyclerView
    private lateinit var tvLoadingDat: TextView
    private lateinit var ok: TextView
    private lateinit var thingList: ArrayList<Thing>
    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        ThgRecyclerView = findViewById(R.id.rvThg)
        tvLoadingDat = findViewById(R.id.tvLoadingData)
        ok = findViewById(R.id.ok)

        ThgRecyclerView.layoutManager = LinearLayoutManager(this)
        ThgRecyclerView.setHasFixedSize(true)

        thingList = arrayListOf<Thing>()

        getThingsData()

        ok.setOnClickListener { startActivity(Intent(this, StartActivity::class.java)) }
    }

    private fun getThingsData() {
        ThgRecyclerView.visibility = View.VISIBLE
        tvLoadingDat.visibility = View.GONE

        dbRef =
            FirebaseDatabase.getInstance("https://japance-1905e-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                thingList.clear()
                if (snapshot.exists()) {
                    for (thingSnap in snapshot.children) {
                        val thData = thingSnap.getValue(Thing::class.java)
                        thingList.add(thData!!)
                    }
                    thingList.sortByDescending { thing -> thing.score }
                    thingList.subList(0, if (thingList.size > 10) 10 else thingList.size)
                    val mAdapter = ThgAdapter(thingList)
                    ThgRecyclerView.adapter = mAdapter

                    ThgRecyclerView.visibility = View.VISIBLE
                    tvLoadingDat.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}