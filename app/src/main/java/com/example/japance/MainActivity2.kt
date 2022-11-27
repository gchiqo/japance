package com.example.japance

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {

    lateinit var letter: TextView
    lateinit var next: TextView
    lateinit var count: TextView
    lateinit var input: EditText
    lateinit var prBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        letter = findViewById(R.id.letter)
        input = findViewById(R.id.input)
        next = findViewById(R.id.nextthing)
        count = findViewById(R.id.count)
        prBar = findViewById(R.id.prBar)
        prBar.progress = 0;


        val hir = intent.extras?.getStringArray("array")



        if (hir != null) {
            prBar.max = hir.size
            count.text = "0/" + hir.size
            val sav = ArrayList<String>()

            hir.shuffle(Random(System.currentTimeMillis()))
            var n = 0
            var m = 0
            var b = 0
            letter.text = hir[n].split(',')[0]

            next.setOnClickListener {
                input.setText("")
                if (next.text != "Ok") {
                    letter.text = hir[n].split(',')[1]
                    next.setTextColor(Color.GREEN);
                    next.text = "Ok"
                    sav.add(hir[n])
                    n++; m++
                } else {
                    next.setTextColor(Color.RED)
                    next.text = "don't konw"
                    count.text = (n - m).toString() + "/" + hir.size
                    if (n < hir.size)
                        letter.text = hir[n].split(',')[0]
                    else {
                        next.visibility = View.GONE
                        letter.text = sav[b].split(',')[0]
                    }
                }
            }

            fun nexthir() {
                if (input.text.toString() == hir[n].split(',')[1]) {
                    input.setText("")
                    n++
                    count.text = (n - m).toString() + "/" + hir.size
                    prBar.progress = n - m
                    if (n < hir.size)
                        letter.text = hir[n].split(',')[0]
                    else if (b < m) {
                        next.visibility = View.GONE
                        letter.text = sav[b].split(',')[0]
                    } else {
                        letter.text = "おめでとう"
                        next.visibility = View.GONE
                    }
                }
            }

            fun nextsav() {
                if (input.text.toString() == sav[b].split(',')[1]) {
                    next.visibility = View.GONE
                    input.setText("")
                    b++
                    count.text = (n - m + b).toString() + "/" + hir.size
                    prBar.progress = n - m + b
                    if (b < m)
                        letter.text = sav[b].split(',')[0]
                    else {
                        letter.text = "おめでとう"
                        next.visibility = View.GONE
                    }
                }
            }

            input.addTextChangedListener {
                if (n < hir.size)
                    nexthir();
                else if (b < sav.size)
                    nextsav()
            }
        } else {
            next.text = "ばか,baka"
        }
    }
}