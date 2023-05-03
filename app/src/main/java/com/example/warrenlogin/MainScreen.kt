package com.example.warrenlogin

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setTheme(R.style.Theme_WarrenLogin)
        //Thread.sleep(10000)
       // val splashScreen = installSplashScreen()
        setContentView(R.layout.activity_main_screen)
       // setContentView(R.layout.activity_splash)
        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#ED184A")
    }
}