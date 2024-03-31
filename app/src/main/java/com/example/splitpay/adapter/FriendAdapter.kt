package com.example.splitpay.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.splitpay.databinding.UserItemBinding
import com.example.splitpay.models.User


class FriendAdapter(private val onItemClick: (Int) -> Unit) :ListAdapter<User,FriendAdapter.FriendViewHolder>(ComparatorDiffUtil()){
    inner class FriendViewHolder(private val binding:UserItemBinding):RecyclerView.ViewHolder(binding.root) {
           fun bind(item:User){
              binding.nametv.text=item.name
              binding.emailtv.text=item.email
              binding.itemCheckbox.setOnCheckedChangeListener { _, isChecked ->
                     if(isChecked){
                           onItemClick(item.userId!!.toInt())
                     }
                     else{
                         onItemClick(-item.userId!!.toInt())
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
    class ComparatorDiffUtil: DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}