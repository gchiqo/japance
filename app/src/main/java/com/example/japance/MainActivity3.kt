package com.example.japance

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class MainActivity3 : AppCompatActivity() {

    var array: MutableList<String> = ArrayList()
    lateinit var goj: TextView
    lateinit var dak: TextView
    lateinit var yog: TextView
    lateinit var yod: TextView
    lateinit var gojGrid: GridLayout
    lateinit var dakGrid: GridLayout
    lateinit var yogGrid: GridLayout
    lateinit var yodGrid: GridLayout

    lateinit var button: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        goj = findViewById(R.id.goj)
        dak = findViewById(R.id.dak)
        yog = findViewById(R.id.yog)
        yod = findViewById(R.id.yod)
        gojGrid = findViewById(R.id.gojGrid)
        dakGrid = findViewById(R.id.dakGrid)
        yogGrid = findViewById(R.id.yogGrid)
        yodGrid = findViewById(R.id.yodGrid)

        button = findViewById<Button>(R.id.button)

        var g: Array<String> = emptyArray()
        var d: Array<String> = emptyArray()
        var yg: Array<String> = emptyArray()
        var yd: Array<String> = emptyArray()
        if (intent.extras?.getString("letters") == "kat") {
            g = arrayOf(
                "ア,a", "イ,i", "ウ,u", "エ,e", "オ,o",
                "カ,ka", "キ,ki", "ク,ku", "ケ,ke", "コ,ko",
                "サ,sa", "シ,shi", "ス,su", "セ,se", "ソ,so",
                "タ,ta", "チ,chi", "ツ,tsu", "テ,te", "ト,to",
                "ナ,na", "ニ,ni", "ヌ,nu", "ネ,ne", "ノ,no",
                "ハ,ha", "ヒ,hi", "フ,fu", "ヘ,he", "ホ,ho",
                "マ,ma", "ミ,mi", "ム,mu", "メ,me", "モ,mo",
                "ヤ,ya", "ユ,yi", "ヨ,yu",
                "ラ,ra", "リ,ri", "ル,ru", "レ,re", "ロ,ro",
                "ワ,wa", "ヲ,wo",
                "ン,n"
            )
            d = arrayOf(
                "ガ,ga", "ギ,gi", "グ,gu", "ゲ,ge", "ゴ,go",
                "ザ,za", "ジ,ji", "ズ,zu", "ゼ,ze", "ゾ,zo",
                "ダ,da", "ヂ,dji", "ヅ,dzu", "デ,de", "ド,do",
                "バ,ba", "ビ,bi", "ブ,bu", "ベ,be", "ボ,bo",
                "パ,pa", "ピ,pi", "プ,pu", "ペ,pe", "ポ,po",
            )
            yg = arrayOf(
                "キャ,kya", "キュ,kyu", "キョ,kyo",
                "シャ,sha", "シュ,shu", "ショ,sho",
                "チャ,cha", "チュ,chu", "チョ,cho",
                "ニャ,nya", "ニュ,nyu", "ニョ,nyo",
                "ヒャ,hya", "ヒュ,hyu", "ヒョ,hyo",
                "ミャ,mya", "ミュ,myu", "ミョ,myo",
                "リャ,rya", "リュ,ryu", "リョ,ryo"
            )
            yd = arrayOf(
                "ギャ,gya", "ギュ,gyu", "ギョ,gyo",
                "ジャ,ja", "ジュ,ju", "ジョ,jo",
                "ビャ,bya", "ビュ,byu", "ビョ,byo",
                "ピャ,pya", "ピュ,pyu", "ピョ,pyo"
            )
        } else {
            g = arrayOf(
                "あ,a", "い,i", "う,u", "え,e", "お,o",
                "か,ka", "き,ki", "く,ku", "け,ke", "こ,ko",
                "さ,sa", "し,shi", "す,su", "せ,se", "そ,so",
                "た,ta", "ち,chi", "つ,tsu", "て,te", "と,to",
                "な,na", "に,ni", "ぬ,nu", "ね,ne", "の,no",
                "は,ha", "ひ,hi", "ふ,fu", "へ,he", "ほ,ho",
                "ま,ma", "み,mi", "む,mu", "め,me", "も,mo",
                "や,ya", "ゆ,yu", "よ,yo",
                "ら,ra", "り,ri", "る,ru", "れ,re", "ろ,ro",
                "わ,wa", "を,wo", "ん,n"
            )
            d = arrayOf(
                "が,ga", "ぎ,gi", "ぐ,gu", "げ,ge", "ご,go",
                "ざ,za", "じ,ji", "ず,zu", "ぜ,ze", "ぞ,zo",
                "だ,da", "ぢ,dji", "づ,dzu", "で,de", "ど,do",
                "ば,ba", "び,bi", "ぶ,bu", "べ,be", "ぼ,bo",
                "ぱ,pa", "ぴ,pi", "ぷ,pu", "ぺ,pe", "ぽ,po"
            )
            yg = arrayOf(
                "きゃ,kya", "きゅ,kyu", "きょ,kyo",
                "しゃ,sha", "しゅ,shu", "しょ,sho",
                "ちゃ,cha", "ちゅ,chu", "ちょ,cho",
                "にゃ,nya", "にゅ,nyu", "にょ,nyo",
                "ひゃ,hya", "ひゅ,hyu", "ひょ,hyo",
                "みゃ,mya", "みゅ,myu", "みょ,myo",
                "りゃ,rya", "りゅ,ryu", "りょ,ryo"
            )
            yd = arrayOf(
                "ぎゃ,gya", "ぎゅ,gyu", "ぎょ,gyo",
                "じゃ,ja", "じゅ,ju", "じょ,jo",
                "びゃ,bya", "びゅ,byu", "びょ,byo",
                "ぴゃ,pya", "ぴゅ,pyu", "ぴょ,pyo"
            )
        }

        displayLetters(g, gojGrid)
        displayLetters(d, dakGrid)
        displayLetters(yg, yogGrid)
        displayLetters(yd, yodGrid)

        forTypeClick(goj, gojGrid, g)
        forTypeClick(dak, dakGrid, d)
        forTypeClick(yog, yogGrid, yg)
        forTypeClick(yod, yodGrid, yd)


        //  go on another page with selected data as aray
        button.setOnClickListener {
            val inte = Intent(this, MainActivity2::class.java)
            inte.putExtra("array", array.toTypedArray())
            startActivity(inte)
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

    fun forTypeClick(titl: TextView, grid: GridLayout, arr: Array<String>) {
        titl.setOnClickListener {
            if (titl.currentTextColor != Color.MAGENTA) {
                for (i in 0 until grid.childCount) {
                    val child: TextView = grid.getChildAt(i) as TextView
                    child.setTextColor(Color.MAGENTA)
                }
                titl.setTextColor(Color.MAGENTA)
                arr.forEach {
                    if (!array.contains(it))
                        array.add(it)
                }
            } else {
                for (i in 0 until grid.childCount) {
                    val child: TextView = grid.getChildAt(i) as TextView
                    child.setTextColor(Color.WHITE)
                }
                titl.setTextColor(Color.WHITE)
                arr.forEach {
                    if (array.contains(it))
                        array.remove(it)
                }
            }

        }
    }

    fun displayLetters(what: Array<String>, where: GridLayout) {
        what.forEach {
            val chl = TextView(this, null, 0, R.style.choseLetterStyle)
            chl.text = it
            chl.setOnClickListener { onLetterClick(chl) }
            where.addView(chl)
        }
    }
}