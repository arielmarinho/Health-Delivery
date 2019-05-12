package com.ariel.healthdelivery

import android.app.DownloadManager
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import com.ariel.healthdelivery.model.Order
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Register_Order.setOnClickListener{

            if (order_name.text.toString().isNullOrEmpty() || phone.text.toString().isNullOrEmpty() ||
                value_min.text.toString().isNullOrEmpty()|| value_max.text.toString().isNullOrEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Preencha os todos os campos", Toast.LENGTH_SHORT
                ).show()
            }
            else if(value_min.text.toString().toDouble() >= value_max.text.toString().toDouble()) {

                Toast.makeText(
                    applicationContext,
                    "Valor minimo nao pode ser maior ou igual ao  valor maximo", Toast.LENGTH_SHORT
                ).show()
            }
            else if(value_max.text.toString().toDouble() <= value_min.text.toString().toDouble()) {

                Toast.makeText(
                    applicationContext,
                    "Valor maximo nao pode ser menor ou igual ao  valor minimo", Toast.LENGTH_SHORT
                ).show()
            }
            else {
                if (setUpMap()){

                    saveOrder()
                    startActivity(Intent(this, LocationActivity::class.java))
                }
            }
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
    private fun setUpMap(): Boolean{
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LocationActivity.LOCATION_PERMISSION_REQUEST_CODE

            )
            return false


        }
        else{
            return true
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == LocationActivity.LOCATION_PERMISSION_REQUEST_CODE){
            if (grantResults[0]== 0 ){
                saveOrder()
                startActivity(Intent(this, MainActivity::class.java))
                //startActivity(Intent(this, LocationActivity::class.java))

            }

        }
    }

}
