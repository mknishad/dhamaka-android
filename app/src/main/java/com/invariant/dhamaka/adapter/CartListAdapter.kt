package com.invariant.dhamaka.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.invariant.dhamaka.databinding.ListItemCartBinding
import com.invariant.dhamaka.model.Product

class CartListAdapter(private val clickListener: ProductListener) :
    ListAdapter<Product, CartViewHolder>(CartProductDiffCallback()) {

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder.from(parent)
    }
}

class CartViewHolder private constructor(val binding: ListItemCartBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Product, clickListener: ProductListener) {
        binding.product = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): CartViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemCartBinding.inflate(layoutInflater, parent, false)
            return CartViewHolder(binding)
        }
    }
}

class CartProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem._id == newItem._id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}
