package com.ariel.healthdelivery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    var mDatabase = FirebaseDatabase.getInstance().getReference("orders")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        order_name.setText(intent.getStringExtra("name"))
        phone.setText(intent.getStringExtra("phone"))
        value_min.setText(intent.getStringExtra("value_min"))
        value_max.setText(intent.getStringExtra("value_max"))
        //edit_order.setOnClickListener {

        //}
    }
  //  private fun editOrder(order_id: String) {
    //    mDatabase!!.child(order_id).up

    //}

}
