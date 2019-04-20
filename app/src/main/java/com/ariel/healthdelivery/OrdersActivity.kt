package com.ariel.healthdelivery

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_orders.*

class OrdersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
        fab_plus.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }


}
