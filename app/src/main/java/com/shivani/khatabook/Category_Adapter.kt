package com.shivani.khatabook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class Category_Adapter(private val context: Context, private val categorylist: ArrayList<Catogery_modelclass>) :
    RecyclerView.Adapter<Category_Adapter.CategoryHolder>() {

    class CategoryHolder(itemView: View) : ViewHolder(itemView) {
        var categoryTile = itemView.findViewById<TextView>(R.id.catogery_tile)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_tile, parent, false)
        return CategoryHolder(view)
    }

    override fun getItemCount(): Int {
        return categorylist.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.categoryTile.text = categorylist[position].name
    }
}