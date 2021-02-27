package com.rakuda.simplecolorscreen

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_screen.*

class ScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen)

        // 背景色を設定
        textViewBackground.setBackgroundColor(this.intent.getIntExtra(getString(R.string.intent_key_color), Color.WHITE))
    }
}