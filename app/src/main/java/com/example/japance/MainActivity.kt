package com.example.japance

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    lateinit var hir: Button
    lateinit var kat: Button
    lateinit var text: TextView
    lateinit var linLay: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hir = findViewById(R.id.hiragana)
        kat = findViewById(R.id.katagana)
        text = findViewById(R.id.text)
        linLay = findViewById(R.id.linn)

        var inte = Intent(this, MainActivity3::class.java)
        hir.setOnClickListener{ inte.putExtra("letters", "hir"); startActivity(inte) }
        kat.setOnClickListener{ inte.putExtra("letters", "kat"); startActivity(inte) }

    }
}