package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.MenuItemBinding
import com.example.myapplication.model.MenuItem

typealias Click = (position: Int) -> Unit

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    lateinit var click: Click
    private var activePosition = 0
    private val items = mutableListOf<MenuItem>()
    fun setItems(list: List<MenuItem>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MenuItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: MenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: MenuItem
        fun bind() {
            model = items[adapterPosition]
            binding.ivImage.setImageResource(model.Image)
            binding.tvTitle.text = model.title
            if (adapterPosition == activePosition) {
                binding.active.visibility = View.VISIBLE
                binding.root.setBackgroundResource(android.R.color.holo_purple)
            }else{
                binding.active.visibility = View.INVISIBLE
                binding.root.setBackgroundResource(R.color.white)
            }
            binding.root.setOnClickListener {
                activePosition = adapterPosition
                click(adapterPosition)
                notifyDataSetChanged()
            }
        }
    }
}