package com.oliversolutions.dev.usermanager.features.user.presentation.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oliversolutions.dev.usermanager.databinding.UserViewItemBinding
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User

class UserGridAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<User, UserGridAdapter.UserViewHolder>(DiffCallback) {

    class OnClickListener(val clickListener: (user: User) -> Unit) {
        fun onClick(user: User) = clickListener(user)
    }

    class UserViewHolder(private var binding: UserViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.user = user
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(user)
        }
    }
}
