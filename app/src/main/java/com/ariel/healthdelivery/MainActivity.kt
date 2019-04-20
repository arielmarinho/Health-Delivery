package com.ariel.healthdelivery

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationView = nav as BottomNavigationView
        navigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.Logout -> {
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
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
