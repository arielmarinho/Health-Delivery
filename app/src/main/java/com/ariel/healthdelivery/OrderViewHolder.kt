package com.ariel.healthdelivery

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.ariel.healthdelivery.model.Order
import com.google.firebase.database.FirebaseDatabase

class OrderViewHolder(itemView: View,
                      val clickListener: (Order) -> Unit) : RecyclerView.ViewHolder(itemView){
    var mDatabase = FirebaseDatabase.getInstance().getReference("orders")
    private val name: TextView
    private val phone: TextView
    private val min: TextView
    private val max: TextView
    private var delete: Button
    private var card : CardView

    init {
        name = itemView.findViewById(R.id.show_name) as TextView
        phone = itemView.findViewById(R.id.show_phone) as TextView
        min = itemView.findViewById(R.id.show_min) as TextView
        max = itemView.findViewById(R.id.show_max) as TextView
        delete = itemView.findViewById(R.id.delete) as Button
        card = itemView.findViewById(R.id.card)as CardView
    }
    fun setModel(model: Order?) {
        if (model == null || model!!.name == null) return
            itemView.tag = model.order_id
            name.text = model.name
            phone.text = model.phone
            min.text = model.min.toString()
            max.text = model.max.toString()
            delete.setOnClickListener {
                deleteToDoItem(model.order_id!!)
            }
        card.setOnClickListener {
            clickListener(model)
            //deleteToDoItem(model.order_id!!)
        }
    }

    private fun deleteToDoItem(todoId: String) {
        mDatabase!!.child(todoId).setValue(null)

    }



}
