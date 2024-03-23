package com.example.splitpay.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.splitpay.databinding.GroupItemBinding
import com.example.splitpay.models.GroupResponse



class GroupAdapter(private val onItemClick: (GroupResponse) -> Unit) :ListAdapter<GroupResponse,GroupAdapter.GroupViewHolder>(ComparatorDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val binding=GroupItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val item=getItem(position)
        item.let {
            holder.bind(it)
        }
    }

   inner class GroupViewHolder(private val binding:GroupItemBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(group:GroupResponse){
             binding.title.text=group.name
             binding.created.text=group.created
             binding.root.setOnClickListener {
                  onItemClick(group)
             }
        }

    }


}

class ComparatorDiffUtil: DiffUtil.ItemCallback<GroupResponse>() {
    override fun areItemsTheSame(oldItem: GroupResponse, newItem: GroupResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GroupResponse, newItem: GroupResponse): Boolean {
        return oldItem == newItem
    }
}