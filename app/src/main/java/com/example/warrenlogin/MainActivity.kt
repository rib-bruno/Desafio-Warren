package com.example.warrenlogin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.warrenlogin.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#FFFFFF")


        binding.btnEntrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()

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
                    login(it)
                }
            }

        }
    }

    private fun login(view: View) {

        val progressbar = binding.progressBar
        progressbar.visibility = View.VISIBLE

        binding.btnEntrar.isEnabled = false
        binding.btnEntrar.setTextColor(Color.parseColor("#FFFFFF"))

        Handler(Looper.getMainLooper()).postDelayed({
            navigateMainScreen()
            val snackbar = Snackbar.make(view, "Login efetuado com sucesso", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }, 3000)
    }

    private fun navigateMainScreen() {
        val intent = Intent(this, MainScreen::class.java)
        startActivity(intent)
        finish()
    }
}