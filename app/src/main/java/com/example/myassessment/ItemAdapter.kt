package com.example.myassessment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val groupedItems: Map<Int, List<Item>>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val headersAndItems = mutableListOf<Any>()

    init {
        // Flatten the grouped map into a list of headers and items
        groupedItems.forEach { (listId, items) ->
            headersAndItems.add(listId)  // Add the listId as a header
            headersAndItems.addAll(items)  // Add all items under that listId
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (headersAndItems[position]) {
            is Int -> VIEW_TYPE_HEADER  // Header for listId
            is Item -> VIEW_TYPE_ITEM  // Item
            else -> throw IllegalArgumentException("Unknown type at position $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.header_view, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
            ItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                val listId = headersAndItems[position] as Int
                holder.listIdTextView.text = "List ID: $listId"
            }
            is ItemViewHolder -> {
                val item = headersAndItems[position] as Item
                holder.nameTextView.text = "Name: ${item.name}"
                holder.listIdTextView.text = "ID: ${item.id}"
            }
        }
    }

    override fun getItemCount(): Int = headersAndItems.size

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listIdTextView: TextView = itemView.findViewById(R.id.headerListId)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listIdTextView: TextView = itemView.findViewById(R.id.listId)
        val nameTextView: TextView = itemView.findViewById(R.id.name)
    }

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_ITEM = 1
    }
}
