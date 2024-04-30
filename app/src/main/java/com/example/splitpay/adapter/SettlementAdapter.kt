package com.example.splitpay.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.splitpay.databinding.SettlementItemBinding
import com.example.splitpay.models.ResItem
import com.example.splitpay.models._ResItem


class SettlementAdapter(): ListAdapter<_ResItem, SettlementAdapter.SettlementViewHolder>(ComparatorDiffUtil()) {



    inner class SettlementViewHolder(private val binding: SettlementItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:_ResItem){

            binding.name1tv.text= item.payer!!.name
            binding.name2tv.text= item.payee!!.name
            binding.amounttv.text="₹ ${item.amount.toString()}"


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettlementViewHolder {
        val binding= SettlementItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SettlementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SettlementViewHolder, position: Int) {
        val item=getItem(position)
        item.let {
            holder.bind(it)
        }
    }


    class ComparatorDiffUtil: DiffUtil.ItemCallback<_ResItem>() {


        override fun areItemsTheSame(oldItem: _ResItem, newItem: _ResItem): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: _ResItem, newItem: _ResItem): Boolean {
            return oldItem==newItem
        }
    }


}

