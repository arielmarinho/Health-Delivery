package com.ariel.healthdelivery

import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import com.ariel.healthdelivery.model.Order
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        Register_Order.setOnClickListener{
            saveOrder()
            startActivity(Intent(this, LocationActivity::class.java))


        }

    }

    fun saveOrder(){
        val name = order_name.text.toString().trim()
        val min = value_min.text.toString().toDouble()
        val max = value_max.text.toString().toDouble()
        val num_phone = phone.text.toString()
        val ref = FirebaseDatabase.getInstance().getReference("orders")
        val orderId = ref.push().key

        val order = Order(orderId!!,name,min, max,num_phone)
        ref.child(orderId).setValue(order).addOnCompleteListener{
            Toast.makeText(applicationContext,"Order saved sucessfully", Toast.LENGTH_SHORT).show()
        }
        return

    }

}
