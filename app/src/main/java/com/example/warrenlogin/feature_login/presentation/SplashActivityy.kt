package com.example.warrenlogin.feature_login.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.warrenlogin.MainActivity
import com.example.warrenlogin.MainScreen
import com.example.warrenlogin.R

class SplashActivityy : AppCompatActivity() {
    private  val splashViewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_activityy)

        startAccessObserver()
    }

    private fun startAccessObserver () {
        splashViewModel.accessState.observe(this) {
            if (it) {
                startActivity(Intent(this, MainScreen::class.java))
                finish()
            } else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

}