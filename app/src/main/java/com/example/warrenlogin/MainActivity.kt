package com.example.warrenlogin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.warrenlogin.databinding.ActivityMainBinding
import com.example.warrenlogin.feature_login.domain.util.Resource
import com.example.warrenlogin.feature_login.presentation.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#FFFFFF")

        binding.btnEntrar.setOnClickListener {
            val email = binding.editEmail.text.toString().trim()
            val password = binding.editPassword.text.toString().trim()


            when {
                email.isEmpty() -> {
                    binding.editEmail.error = "Preencha o Email"
                }
                password.isEmpty() -> {
                    binding.editPassword.error = "Preencha a Senha"
                }

                !email.contains("@warrenbrasil.com") -> {
                    val snackbar = Snackbar.make(it, "E-mail inválido!", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
                password.length <= 5 -> {
                    val snackbar = Snackbar.make(
                        it,
                        "A senha precisa ter pelo menos 6 caracteres!",
                        Snackbar.LENGTH_SHORT
                    )
                    snackbar.show()
                }
                else -> {
                    login()
                }
            }

        }
    }

    private fun login() {
        val email = binding.editEmail.text.toString().trim()
        val password = binding.editPassword.text.toString().trim()
        val progressbar = binding.progressBar
        progressbar.visibility = View.VISIBLE

        binding.btnEntrar.isEnabled = false
        binding.btnEntrar.setTextColor(Color.parseColor("#FFFFFF"))

        loginViewModel.doLogin(email, password)
        Handler(Looper.getMainLooper()).postDelayed({
            navigateMainScreen()
           // val snackbar = Snackbar.make(view, "Login efetuado com sucesso", Snackbar.LENGTH_SHORT)
           // snackbar.show()
        }, 3000)
    }

    private fun navigateMainScreen() {
        val intent = Intent(this, MainScreen::class.java)
        startActivity(intent)
        finish()
    }

//    private fun startLoginObserver() {
//        loginViewModel.loginState.observe(this) {
//            when(it) {
//                is Resource.Success -> {
//                    lifecycleScope.launch {
//
//                    }
//                }
//            }
//        }
//    }
}