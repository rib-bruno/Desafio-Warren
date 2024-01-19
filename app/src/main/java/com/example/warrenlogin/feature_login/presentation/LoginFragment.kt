package com.example.warrenlogin.feature_login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.warrenlogin.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    //inflar o layout associado ao fragment e retornar a view
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeViewModel()
    }
    private fun setupUI() {
        binding.btnEntrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            val snackBar: Snackbar

            when {
                email.isEmpty() -> {
                    binding.editEmail.error = "Preencha o Email!"
                }
                password.isEmpty() -> {
                    binding.editPassword.error = "Preencha a senha!"
                }
                email.contains("@warrenbrasil.com") -> {
                    snackBar = Snackbar.make(it, "E-mail inválido!", Snackbar.LENGTH_SHORT)
                    snackBar.show()
                }
                password.length <= 5 -> {
                    snackBar = Snackbar.make(
                        it,
                        "A senha precisa ter pelo menos 6 caracteres!",
                        Snackbar.LENGTH_SHORT
                    )
                    snackBar.show()
                }
                else -> {
                    viewModel.doLogin(email, password)
                }
            }
        }
    }
    private fun observeViewModel() {
        viewModel.loginState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoginState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is LoginState.Success -> {
                    //executar ações após um login bem-sucedido; navegar pra proxima tela
                    val access = state.access
                }

                is LoginState.Error -> {
                    //lidar com erros de login/exibir mensagens de erro, etc.
                    val errorMessage = state.message
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}