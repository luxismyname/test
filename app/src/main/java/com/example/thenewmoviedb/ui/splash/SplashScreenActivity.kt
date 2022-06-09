package com.example.thenewmoviedb.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.thenewmoviedb.MainActivity
import com.example.thenewmoviedb.R
import com.example.thenewmoviedb.ui.detail.MovieDetailActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        Handler().postDelayed({
           val intent = Intent(this, MovieDetailActivity::class.java)
            startActivity(intent)
            finish()

        },3000)

    }
}