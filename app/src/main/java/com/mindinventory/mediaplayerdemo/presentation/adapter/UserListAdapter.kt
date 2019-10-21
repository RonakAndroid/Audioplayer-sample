package com.mindinventory.mediaplayerdemo.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mindinventory.mediaplayerdemo.R
import com.mindinventory.mediaplayerdemo.presentation.model.Example
import kotlinx.android.synthetic.main.row_user_info.view.*
import java.io.File

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewModel>() {

    private var usersList = mutableListOf<Example>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewModel {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_user_info, parent, false)
        return UserViewModel(v)
    }

    fun loadAllUsers(example: ArrayList<Example>) {
        usersList.addAll(example)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: UserViewModel, position: Int) {
        holder.bind(usersList[position])
    }

    inner class UserViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(example: Example) {
            itemView.ivprofilePhoto.load(example.picture?.large)
            itemView.tvDisplayName.text = example.name?.title + " " + example.name?.first+ " " + example.name?.last
            itemView.tvGender.text = example.gender
            itemView.tvEmail.text = example.email
            itemView.tvAddress.text = example.street?.number.toString() + example.street?.name
            itemView.tvAge.text = example.dob?.age.toString()
            itemView.tvPhone.text = example.phone
        }
    }
}