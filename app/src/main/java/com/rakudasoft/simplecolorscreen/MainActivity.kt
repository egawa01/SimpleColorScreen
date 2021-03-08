package com.rakuda.simplecolorscreen

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val tag = "Simple ColorScreen"
    // private val appID = "ca-app-pub-3940256099942544~3347511713" // テスト用
    //   private val adUnitId="ca-app-pub-3940256099942544/6300978111" // テスト用


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag,"OnCreate ")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // spinnerColorの文字を大きくするための設定
        val adapter = ArrayAdapter.createFromResource(this, R.array.colors, R.layout.spinner_item)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerColor.adapter = adapter

        //buttonGoのクリックイベント定義
        buttonGo.setOnClickListener() {
            val intent = Intent(this, ScreenActivity::class.java)
            intent.putExtra(getString(R.string.intent_key_color), getColorInt(spinnerColor.selectedItem.toString()))
            startActivity(intent)
        }

        //  設定読み込みとUI反映
        val savedSelection = getSharedPreferences(getString(R.string.pref_filename), MODE_PRIVATE).getInt(getString(R.string.pref_label_color), 0)
        spinnerColor.setSelection(savedSelection)

        MobileAds.initialize(this)
        val adRequest : AdRequest = AdRequest.Builder()
            .build()
        adView.loadAd(adRequest)

        Log.d(tag, "Saved Selection is ${savedSelection.toString()}")
    }

    override fun onDestroy() {
        Log.d(tag,"onDestroy ")

        // 設定書き込み
        val saveSelection = spinnerColor.selectedItemPosition
        getSharedPreferences(getString(R.string.pref_filename), MODE_PRIVATE).edit()
            .putInt(getString(R.string.pref_label_color),saveSelection)
            .apply()

        Log.d(tag, "Save Selection is ${saveSelection.toString()}")

        super.onDestroy()
    }

    // メニュー定義
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // メニューアイテム設定
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    // メニュー洗濯イベント処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.close -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 色文字列と色値の変換
    private fun getColorInt(colorString: String): Int {
        return when (colorString) {
            "White" -> Color.WHITE
            "Black" -> Color.BLACK
            "Red" -> Color.RED
            "Green" -> Color.GREEN
            "Blue" -> Color.BLUE
            "Yellow" -> Color.YELLOW
            else -> Color.WHITE
        }
    }
}
