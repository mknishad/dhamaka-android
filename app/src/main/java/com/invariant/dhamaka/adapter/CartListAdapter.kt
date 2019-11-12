package com.invariant.dhamaka.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.invariant.dhamaka.databinding.ListItemCartBinding
import com.invariant.dhamaka.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

class CartListAdapter(private val clickListener: ProductListener) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(ProductDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CartViewHolder -> {
                val productItem = getItem(position) as DataItem.ProductItem
                holder.bind(productItem.product, clickListener)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> CartViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.ProductItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<Product>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.ProductItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }
}

class CartViewHolder private constructor(val binding: ListItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
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

