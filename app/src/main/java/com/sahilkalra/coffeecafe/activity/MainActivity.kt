package com.sahilkalra.coffeecafe.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.sahilkalra.coffeecafe.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            val startAct=Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(startAct)
        },2000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}