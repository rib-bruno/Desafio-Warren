package com.example.warrenlogin.feature_user.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.warrenlogin.R
import com.example.warrenlogin.databinding.UserGoalsItemBinding
import com.example.warrenlogin.feature_user.domain.entities.User
import com.example.warrenlogin.other.gone
import com.example.warrenlogin.other.loadFromPicasso

class UserGoalsAdapter(private val context: Context, private val userList: List<User>) :
    RecyclerView.Adapter<UserGoalsAdapter.UserGoalsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGoalsViewHolder {
        val listItemUserGoals =
            UserGoalsItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return UserGoalsViewHolder(listItemUserGoals)
    }

    override fun onBindViewHolder(holder: UserGoalsViewHolder, position: Int) {

        holder.bind(userList[position])
    }

    override fun getItemCount() = userList.size

    inner class UserGoalsViewHolder(private val binding: UserGoalsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            with(binding) {
                txtName.text = user.name
                txtTotalBalance.text = user.totalBalance.toString()
                txtGoalAmount.text = user.goalAmount.toString()
                txtGoalDate.text = user.goalDate
                imgUser.loadFromPicasso(
                    user.background.small,
                    R.drawable.ic_baseline_account_circle_24,
                    ::onLoadImageSuccess,
                    ::onLoadImageError

                )
            }
        }

//        val imgUser = binding.imgUser
//        val name = binding.txtName
//        val totalBalance = binding.txtTotalBalance
//        val goalAmount = binding.txtGoalAmount
//        val goalDate = binding.txtGoalDate

        private fun onLoadImageSuccess() {
            binding.progressBar.gone()
        }

        private fun onLoadImageError() {
            binding.progressBar.gone()
        }
    }


}