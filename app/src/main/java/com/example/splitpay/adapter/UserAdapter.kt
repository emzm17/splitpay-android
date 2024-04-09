package com.example.splitpay.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.splitpay.databinding.UserItemBinding
import com.example.splitpay.models.User


class UserAdapter(private val onItemclick: (User) -> Unit, private val isShow: Boolean, private val userId: Int): androidx.recyclerview.widget.ListAdapter<User, UserAdapter.UserViewHolder>(ComparatorDiffUtil()) {


    inner class UserViewHolder(private val binding:UserItemBinding):RecyclerView.ViewHolder(binding.root){
             fun bind(item:User){
                  if(isShow){
                      binding.itemCheckbox.visibility= View.GONE
                      binding.addBtn.visibility=View.VISIBLE
                      binding.addBtn.setOnClickListener {
                             onItemclick(item)
                      }
                  }
                  else{
                      binding.itemCheckbox.visibility= View.GONE
                      binding.addBtn.visibility=View.GONE
                  }
                  if(userId==item.userId){
                      binding.itemCheckbox.visibility= View.GONE
                      binding.addBtn.visibility=View.GONE
                  }

                  binding.nametv.text=item.name
                  binding.emailtv.text=item.email
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding=UserItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item=getItem(position)
        item.let {
            holder.bind(it)
        }
    }
}