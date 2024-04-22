//package com.example.splitpay.adapter
//
//import com.example.splitpay.databinding.User1ItemBinding
//import kotlin.reflect.KFunction1
//
//
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.example.splitpay.databinding.UserItemBinding
//import com.example.splitpay.models.User
//
//
//class RequestAdapter(private val onItemclick: (User) -> Unit): androidx.recyclerview.widget.ListAdapter<User, RequestAdapter.UserViewHolder>(ComparatorDiffUtil()) {
//
//
//    inner class UserViewHolder(private val binding: User1ItemBinding):RecyclerView.ViewHolder(binding.root){
//        fun bind(item:User){
//            binding.addBtn.setOnClickListener {
//                onItemclick(item)
//            }
//
//            binding.nametv.text=item.
//            binding.emailtv.text=item.email
//        }
//    }
//    class ComparatorDiffUtil: DiffUtil.ItemCallback<User>() {
//
//        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
//            return oldItem.userId == newItem.userId
//        }
//
//        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
//        val binding=User1ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        return UserViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        val item=getItem(position)
//        item.let {
//            holder.bind(it)
//        }
//    }
//}