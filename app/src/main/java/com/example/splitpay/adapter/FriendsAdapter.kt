package com.example.splitpay.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.splitpay.databinding.UserItemBinding
import com.example.splitpay.models.DataItem
import androidx.recyclerview.widget.ListAdapter
import com.example.splitpay.databinding.FriendItemBinding


class FriendsAdapter():ListAdapter<DataItem,FriendsAdapter.FriendsViewHolder>(ComparatorDiffUtil()) {
    inner class FriendsViewHolder(private val binding:FriendItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item:DataItem){
            binding.nametv.text=item.name
            binding.emailtv.text=item.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val binding=FriendItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FriendsViewHolder (binding)
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        val item=getItem(position)
        item.let {
            holder.bind(it)
        }
    }
    class ComparatorDiffUtil: DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.userId==newItem.userId
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem==newItem
        }


    }
}