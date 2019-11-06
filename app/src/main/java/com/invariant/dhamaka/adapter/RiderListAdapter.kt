package com.invariant.dhamaka.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.invariant.dhamaka.R
import com.invariant.dhamaka.databinding.ListItemRiderBinding
import com.invariant.dhamaka.model.Rider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

class RiderAdapter(val clickListener: RiderListener) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(RiderDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val riderItem = getItem(position) as DataItem.RiderItem
                holder.bind(riderItem.rider, clickListener)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.RiderItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<Rider>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.RiderItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }
}

class ViewHolder private constructor(val binding: ListItemRiderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Rider, clickListener: RiderListener) {
        binding.rider = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemRiderBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}

class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun from(parent: ViewGroup): TextViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.header, parent, false)
            return TextViewHolder(view)
        }
    }
}


/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class RiderDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

class RiderListener(val clickListener: (rider: Rider) -> Unit) {
    fun onClick(rider: Rider) = clickListener(rider)
}

sealed class DataItem {
    data class RiderItem(val rider: Rider) : DataItem() {
        override val id: String
            get() = rider._id!!
    }

    object Header : DataItem() {
        override val id: String
            get() = ""
    }

    abstract val id: String
}
