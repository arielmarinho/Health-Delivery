package com.ariel.healthdelivery

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.ariel.healthdelivery.model.Order

class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val name: TextView
    private val phone: TextView
    private val min: TextView
    private val max: TextView
    init {
        name = itemView.findViewById(R.id.show_name) as TextView
        phone = itemView.findViewById(R.id.show_phone) as TextView
        min = itemView.findViewById(R.id.show_min) as TextView
        max = itemView.findViewById(R.id.show_max) as TextView
    }
    fun setModel(model: Order?) {
        if (model == null || model!!.name == null) return
        itemView.tag = model.order_id
        name.text = model.name
        phone.text = model.phone
        min.text = model.min.toString()
        max.text = model.max.toString()


    }
}