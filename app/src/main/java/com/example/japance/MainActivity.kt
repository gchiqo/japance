package com.example.japance

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    lateinit var hir: Button
    lateinit var kat: Button
    lateinit var text: TextView
    lateinit var linLay: LinearLayout
    lateinit var words: Button
    lateinit var add: Button
    lateinit var ask: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hir = findViewById(R.id.hiragana)
        kat = findViewById(R.id.katagana)
        text = findViewById(R.id.text)
        linLay = findViewById(R.id.linn)
        words = findViewById(R.id.words)
        add = findViewById(R.id.add)
        ask = findViewById(R.id.ask)



        var inte = Intent(this, MainActivity3::class.java)
        hir.setOnClickListener{ inte.putExtra("letters", "hir"); startActivity(inte) }
        kat.setOnClickListener{ inte.putExtra("letters", "kat"); startActivity(inte) }

        words.setOnClickListener { words.visibility = View.GONE}

        var intent = Intent(this, AddActivity::class.java)
        add.setOnClickListener{  intent.putExtra("typ", true);  startActivity(intent) }
        ask.setOnClickListener{ intent.putExtra("typ", false);  startActivity(intent) }

    }
}

















