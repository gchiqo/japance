package com.example.japance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import androidx.fragment.app.Fragment

class FrameeActivity : AppCompatActivity() {

    val fragment1 = Fragment1()
    val fragment2 = Fragment2()
    val handler = Handler()
    lateinit var seekBar : SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_framee)

        seekBar = findViewById(R.id.seekBar)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentLayout, fragment1)
            .commit()

        seekBar.progress
        handler.postDelayed({ dothing2() }, seekBar.progress.toLong()*100)
    }
//thinggg
    fun dothing2(){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentLayout, fragment2).commit()
        handler.postDelayed({ dothing1() }, seekBar.progress.toLong()*100)
    }    fun dothing1(){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentLayout, fragment1).commit()
        handler.postDelayed({ dothing2() }, seekBar.progress.toLong()*100)
    }
}