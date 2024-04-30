package com.example.splitpay.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.splitpay.databinding.UserItemBinding
import com.example.splitpay.models.DataItem
import com.example.splitpay.models.UsersItem
import kotlin.reflect.KFunction2


class FriendAdapter(private val onItemClick: (Boolean,UsersItem)->Unit) :ListAdapter<DataItem,FriendAdapter.FriendViewHolder>(ComparatorDiffUtil()){
    inner class FriendViewHolder(private val binding:UserItemBinding):RecyclerView.ViewHolder(binding.root) {
           fun bind(item:DataItem){
              binding.nametv.text=item.name
              binding.emailtv.text=item.email
              val user=UsersItem(item.userId,item.name,item.email)
              binding.itemCheckbox.setOnCheckedChangeListener { _, isChecked ->
                     if(isChecked){
                         onItemClick(true,user)
                     }
                     else{
                         onItemClick(false,user)
                     }
              }
           }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendAdapter.FriendViewHolder {
        val binding=UserItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendAdapter.FriendViewHolder, position: Int) {
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