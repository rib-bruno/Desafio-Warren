package com.example.warrenlogin

import android.graphics.Color
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warrenlogin.databinding.ActivityMainScreenBinding
import com.example.warrenlogin.feature_user.data.adapters.UserGoalsAdapter
import com.example.warrenlogin.feature_user.domain.entities.User
import com.example.warrenlogin.feature_user.presentation.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : AppCompatActivity() {

    //INICIANDO O BINDING
    private lateinit var binding: ActivityMainScreenBinding

    private val viewModel : UsersViewModel by viewModels()

    private lateinit var progressBar: ProgressBar

    private lateinit var userGoalsAdapter: UserGoalsAdapter

    private val userList: MutableList<User> = mutableListOf()

    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

    //    lifecycle.addObserver(viewModel)


        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#ED184A")
    }

    private fun setupViews(binding: ActivityMainScreenBinding) {
        with(binding) {
            progressBar = userGoalsProgressBar

            recyclerView = userGoalsRecyclerView.apply {
                adapter = userGoalsAdapter
                layoutManager = LinearLayoutManager(context)
            }
        //    root.setOnRefreshListener(this@MainScreen)

        }
    }




//    private fun getImageView() {
//        Picasso.get().load(viewModel.userGoalsLiveData.value.)
//    }
}