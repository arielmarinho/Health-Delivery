package com.ariel.healthdelivery

import android.content.Context
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_call.*
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity


class CallActivity : AppCompatActivity() {
    var number = ""
    companion object {
        private const val CALL_PERMISSION_REQUEST_CODE = 2

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)
        btn_call.setOnClickListener{
            makeCall()
            }
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                CallActivity.CALL_PERMISSION_REQUEST_CODE

            )
            return

        }


        }
    fun makeCall() {
        number = teste_call.text.toString()
        val intent = Intent(Intent.ACTION_CALL);
        intent.data = Uri.parse("tel:$number")
        startActivity(intent)
    }
}



