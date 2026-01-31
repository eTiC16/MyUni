package com.example.myuni.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myuni.data.remote.EntityDto
import com.example.myuni.databinding.ItemEntityBinding

class EntityAdapter(
    private val onClick: (EntityDto) -> Unit
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    private val items = mutableListOf<EntityDto>()

    fun submitList(newItems: List<EntityDto>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class EntityViewHolder(
        private val binding: ItemEntityBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EntityDto) {
            binding.tvProperty1.text = item.property1
            binding.tvProperty2.text = item.property2
            binding.root.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEntityBinding.inflate(inflater, parent, false)
        return EntityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
