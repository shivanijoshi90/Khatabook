package com.shivani.khatabook

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class Data_adapter(val context: Context, val list: ArrayList<IncomeExpense_model>) :
    RecyclerView.Adapter<Data_adapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : ViewHolder(itemView) {
        var txtDataTitle = itemView.findViewById<TextView>(R.id.txtDataTitle)
        var txtDataId = itemView.findViewById<TextView>(R.id.txtDataId)
        var txtDataAmount = itemView.findViewById<TextView>(R.id.txtDataAmount)
        var txtDataNotes = itemView.findViewById<TextView>(R.id.txtDataNotes)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.data_tile, parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.txtDataId.text = list[position].id
        holder.txtDataAmount.text = list[position].amount.toString()
        holder.txtDataNotes.text = list[position].note
        holder.txtDataTitle.text = list[position].title

        holder.txtDataAmount.setOnClickListener {
            var intent = Intent(context, IncomeExpense_Activity::class.java)

            intent.putExtra("title", list[position].title)
            intent.putExtra("amount", list[position].amount)
            intent.putExtra("notes", list[position].note)
            intent.putExtra("date", list[position].date)
            intent.putExtra("time", list[position].time)
            intent.putExtra("id", list[position].id)
            intent.putExtra("category", list[position].category)

            context.startActivity(intent)
        }
    }
}