package com.example.warrenlogin.feature_splash.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.warrenlogin.MainActivity
import com.example.warrenlogin.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splash = installSplashScreen()
        //   setContentView(R.layout.activity_splash2)
        splash.setKeepVisibleCondition {
            true
        }

        startAccessObserver()

    }

    private fun startAccessObserver() {
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



