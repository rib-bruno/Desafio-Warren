package com.example.warrenlogin.feature_user.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.warrenlogin.databinding.UserGoalsItemBinding
import com.example.warrenlogin.feature_user.domain.entities.User

class UserGoalsAdapter (private val context: Context, private val userList: List<User>) :
    RecyclerView.Adapter<UserGoalsAdapter.UserGoalsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGoalsViewHolder {
        val listItemUserGoals = UserGoalsItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return UserGoalsViewHolder(listItemUserGoals)
    }

    override fun onBindViewHolder(holder: UserGoalsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = userList.size

    inner class UserGoalsViewHolder(binding: UserGoalsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val imgUser = binding.imgUser
        val name = binding.txtName
        val totalBalance = binding.txtTotalBalance
        val goalAmount = binding.txtGoalAmount
        val goalDate = binding.txtGoalDate
    }
}