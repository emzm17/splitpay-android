package com.example.splitpay.adapter

import com.example.splitpay.databinding.User1ItemBinding
import kotlin.reflect.KFunction1



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.splitpay.databinding.UserItemBinding
import com.example.splitpay.models.DataItem
import com.example.splitpay.models.DataItem1
import com.example.splitpay.models.User


class RequestAdapter(private val onItemclick: (DataItem1) -> Unit): androidx.recyclerview.widget.ListAdapter<DataItem1, RequestAdapter.UserViewHolder>(ComparatorDiffUtil()) {


    inner class UserViewHolder(private val binding: User1ItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:DataItem1){
            binding.addBtn.setOnClickListener {
                onItemclick(item)
            }
            binding.nametv.text=item.name
            binding.emailtv.text=item.email
        }
    }
    class ComparatorDiffUtil: DiffUtil.ItemCallback<DataItem1>() {
        override fun areItemsTheSame(oldItem: DataItem1, newItem: DataItem1): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(oldItem: DataItem1, newItem: DataItem1): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding=User1ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item=getItem(position)
        item.let {
            holder.bind(it)
        }
    }
}