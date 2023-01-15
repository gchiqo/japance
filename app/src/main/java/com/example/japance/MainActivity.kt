package com.example.japance

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.google.firebase.database.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var letter: TextView
    lateinit var next: TextView
    lateinit var count: TextView
    lateinit var input: EditText
    lateinit var prBar: ProgressBar

    var n = 0
    var m = 0
    var b = 0
    var siz = 0
    var hir: Array<String>? = null
    val sav = ArrayList<String>()

    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        letter = findViewById(R.id.letter)
        input = findViewById(R.id.input)
        next = findViewById(R.id.nextthing)
        count = findViewById(R.id.count)
        prBar = findViewById(R.id.prBar)
        prBar.progress = 0;

        dbRef =
            FirebaseDatabase.getInstance("https://japance-1905e-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("")

        hir = intent.extras?.getStringArray("array")

        if (hir != null) {
            siz = hir!!.size
            prBar.max = siz
            count.text = "0/" + siz


            hir!!.shuffle(Random(System.currentTimeMillis()))

            letter.text = hir!![n].split(',')[0]

            next.setOnClickListener {
                if (next.text == "don't konw") {
                    input.setText("")
                    letter.text = hir!![n].split(',')[1]
                    next.setTextColor(Color.GREEN);
                    next.text = "Ok"
                    sav.add(hir!![n])
                    n++; m++
                } else if (next.text == "Ok") {
                    input.setText("")
                    next.setTextColor(Color.RED)
                    next.text = "don't konw"
                    count.text = (n - m).toString() + "/" + siz
                    if (n < siz)
                        letter.text = hir!![n].split(',')[0]
                    else {
                        next.visibility = View.GONE
                        letter.text = sav[b].split(',')[0]
                    }
                } else if (next.text == "save") {
                    saveThing()
                }else{
                    showThings()
                }
            }

            input.addTextChangedListener {
                if (n < siz)
                    nexthir();
                else if (b < sav.size)
                    nextsav()
            }
        } else {
            next.text = "ばか,baka"
        }
    }

    fun seeDB() {
        dbRef.child("").get().addOnSuccessListener {
            var tthh: String = ""
            for (rav in it.children) {
                tthh += rav.toString()
            }
            letter.text = "isa ${tthh}"
        }.addOnFailureListener {
            letter.text = "Error getting data ${it}";
        }
    }

    fun onThing() {
        letter.textSize = 30F
        letter.text = "enter your name to submit score"
        next.text = "save"
        next.setTextColor(Color.GREEN)
    }

    fun saveThing() {
        letter.text = "saving..."
        val id = dbRef.push().key!!
        val nam = input.text.toString()
        input.setText("")
        val thing = Thing(id, nam, siz)
        dbRef.child(id).setValue(thing)
            .addOnCompleteListener {
                letter.text = "info is saved thanks for thing"
                next.text = "show top 10"
            }.addOnFailureListener { err ->
                letter.text = "sory there was an error\n${err.message}"
            }
    }

    fun showThings(){
        startActivity(Intent(this, FetchingActivity::class.java))
    }

    fun nexthir() {
        if (input.text.toString() == hir!![n].split(',')[1]) {
            input.setText("")
            n++
            count.text = (n - m).toString() + "/" + siz
            prBar.progress = n - m
            if (n < siz)
                letter.text = hir!![n].split(',')[0]
            else if (b < m) {
                next.visibility = View.GONE
                letter.text = sav[b].split(',')[0]
            } else {
                onThing()
            }
        }
    }

    fun nextsav() {
        if (input.text.toString() == sav[b].split(',')[1]) {
            next.visibility = View.GONE
            input.setText("")
            b++
            count.text = (n - m + b).toString() + "/" + siz
            prBar.progress = n - m + b
            if (b < m)
                letter.text = sav[b].split(',')[0]
            else {
                onThing()
            }
        }
    }
}