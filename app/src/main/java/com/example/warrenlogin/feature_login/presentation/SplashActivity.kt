package com.example.warrenlogin.feature_login.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.warrenlogin.MainActivity
import com.example.warrenlogin.MainScreen
import com.example.warrenlogin.R

class SplashActivity: AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_WarrenLogin)



       // setContentView(R.layout.activity_splash)

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