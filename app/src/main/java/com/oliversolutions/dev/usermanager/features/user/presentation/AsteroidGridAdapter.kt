package com.oliversolutions.dev.usermanager.features.user.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oliversolutions.dev.usermanager.databinding.UserViewItemBinding
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User

class UserGridAdapter(val onClickListener: OnClickListener) :
    ListAdapter<User, UserGridAdapter.AsteroidViewHolder>(DiffCallback) {

    class OnClickListener(val clickListener: (asteroid: User) -> Unit) {
        fun onClick(asteroid: User) = clickListener(asteroid)
    }

    class AsteroidViewHolder(private var binding: UserViewItemBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder(
            UserViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(user)
        }
    }
}
