package com.ariel.healthdelivery

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.ariel.healthdelivery.model.Order

class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val name: TextView
    init {
        name = itemView.findViewById(R.id.show_name) as TextView
    }
    fun setModel(model: Order?) {
        if (model == null || model!!.name == null) return
        itemView.tag = model.order_id
        name.text = model.name
    }
}