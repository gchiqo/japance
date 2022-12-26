package com.example.japance

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView

class AddActivity : AppCompatActivity() {
    var array: MutableList<String> = ArrayList()

    lateinit var input: EditText
    lateinit var dlke: LinearLayout
    lateinit var delete: TextView
    lateinit var keep: TextView
    lateinit var list: LinearLayout
    lateinit var addLay: LinearLayout
    lateinit var ok: TextView

    lateinit var cur: TextView
    lateinit var question: TextView
    lateinit var cuid: String

    lateinit var button2: Button
    var index: Int = 0
    var typ: Boolean = true

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        input = findViewById(R.id.input)
        question = findViewById(R.id.question)
        dlke = findViewById(R.id.dlke)
        delete = findViewById(R.id.delete)
        keep = findViewById(R.id.keep)
        list = findViewById(R.id.list)
        button2 = findViewById(R.id.button2)
        ok = findViewById(R.id.ok)
        addLay = findViewById(R.id.addLay)


        typ = intent.extras?.getBoolean("typ", true) == true

        if (typ) ok.visibility = View.GONE
        else addLay.visibility = View.GONE

        val pref = getPreferences(Context.MODE_PRIVATE)
        index = pref.all.size


        pref.all.forEach {
            val chl = TextView(this, null, 0, R.style.choseLetterStyle)
            chl.text = it.value.toString()
            var id = it.key.toString()
            list.addView(chl)
            if (typ)
                chl.setOnClickListener {
                    dlke.visibility = View.VISIBLE; cur = chl; cuid = id
                    question.text = "Delete" + chl.text.toString() + "?"
                }
            else
                chl.setOnClickListener { onLetterClick(chl) }
        }

        //  go on another page with selected data as aray
        ok.setOnClickListener {
            val inte = Intent(this, MainActivity2::class.java)
            inte.putExtra("array", array.toTypedArray())
            startActivity(inte)
        }

        delete.setOnClickListener { Delete(cur, cuid);dlke.visibility = View.GONE; }
        keep.setOnClickListener { dlke.visibility = View.GONE; }
    }

    fun Delete(txt: TextView, id: String) {
        val pref = getPreferences(Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.remove(id)
        editor.commit()
        txt.visibility = View.GONE
    }

    fun Save(view: View) {
        if (input.text.toString().contains(",")) {
            val pref = getPreferences(Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString(index.toString(), input.text.toString())
            editor.commit()

            index++
            val chl = TextView(this, null, 0, R.style.choseLetterStyle)
            chl.text = input.text.toString()
            list.addView(chl)
            input.setText("")
        }
    }

    fun onLetterClick(th: TextView) {
        if (th.currentTextColor == Color.MAGENTA) {
            th.setTextColor(Color.WHITE)
            array.remove(th.text as String)
        } else {
            th.setTextColor(Color.MAGENTA)
            array.add(th.text as String)
        }
    }
}