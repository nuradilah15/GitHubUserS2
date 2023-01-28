package com.example.githubusers2.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.githubusers2.R
import com.example.githubusers2.ui.screen.SplashScreen

class HomeScreenActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val btn_layar: Button = findViewById(R.id.button_layar)
        btn_layar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_layar -> {
                val moveIntent = Intent(this@HomeScreenActivity, SplashScreen::class.java)
                startActivity(moveIntent)
            }
        }
    }
}