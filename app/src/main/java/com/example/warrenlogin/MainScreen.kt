package com.example.warrenlogin

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import com.example.warrenlogin.databinding.ActivityMainScreenBinding
import com.example.warrenlogin.feature_user.presentation.UsersViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : AppCompatActivity() {

    //INICIANDO O BINDING
    private lateinit var binding: ActivityMainScreenBinding
    private val viewModel : UsersViewModel by viewModels()
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#ED184A")
    }

    private fun getImageView() {
        Picasso.get().load(viewModel.userGoalsLiveData.value.)
    }
}