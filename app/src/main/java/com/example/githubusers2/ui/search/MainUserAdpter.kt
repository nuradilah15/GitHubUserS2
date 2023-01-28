package com.example.githubusers2.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubusers2.databinding.ItemUserBinding
import com.example.githubusers2.model.User

class MainUserAdpter : RecyclerView.Adapter<MainUserAdpter.UserViewHolder>(){

    private val listUser = ArrayList<User>()

    private var onItemClick : OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClick : OnItemClickCallback){
        this.onItemClick = onItemClick
    }

    fun setList(user: ArrayList<User>){
        listUser.clear()
        listUser.addAll(user)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root){
        fun binding(user : User){
            binding.root.setOnClickListener{
                onItemClick?.onItemClicked(user)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .centerCrop()
                    .into(photo)
                id.text = "ID : ${user.id}"
                username.text = user.login
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    interface OnItemClickCallback {
        fun onItemClicked(user: User)
    }
}