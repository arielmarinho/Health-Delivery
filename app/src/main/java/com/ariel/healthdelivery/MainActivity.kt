package com.ariel.healthdelivery

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.RelativeLayout
import android.widget.Toast
import com.ariel.healthdelivery.model.Order

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    var mDatabase = FirebaseDatabase.getInstance().getReference("Order")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val orders: ArrayList<String> = ArrayList()








        val navigationView = nav as BottomNavigationView
        navigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.Logout -> {
                    val dialog = AlertDialog.Builder(this)
                    dialog.setTitle("Are You Sure!")
                    dialog.setMessage("Do you want close this app whithout Logout?")
                    dialog.setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int ->
                        finish()
                    })
                    dialog.setNegativeButton("No", { dialogInterface: DialogInterface, i: Int ->
                        FirebaseAuth.getInstance().signOut()
                        startActivity(Intent(this, LoginActivity::class.java))
                    })
                    dialog.show()



                }
                R.id.About ->
                    startActivity(Intent(this,AboutActivity:: class.java))
                R.id.Register ->
                    startActivity(Intent(this,OrdersActivity:: class.java))


            }
                true
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }



}

