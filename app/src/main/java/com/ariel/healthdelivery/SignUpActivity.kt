package com.ariel.healthdelivery

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ariel.healthdelivery.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private val CADASTRO = 1
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        mAuth = FirebaseAuth.getInstance()

        btn_Create.setOnClickListener {


            mAuth.createUserWithEmailAndPassword(
                et_Email2.text.toString(),
                et_Password2.text.toString()

            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    salvaNoRealtimeDatabase()
                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        it.exception?.message,
                        Toast.LENGTH_LONG
                    ).show()
                }


            }
        }

    }

    private fun salvaNoRealtimeDatabase() {
        val user = Usuario(et_Email2.text.toString(), et_Password2.text.toString())

        FirebaseDatabase.getInstance().getReference("Usuario")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(user)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Usuário cadastrado com sucesso!",
                        Toast.LENGTH_LONG
                    ).show()

                    val intent = Intent()
                    intent.putExtra("email", et_Email2.text.toString())
                    intent.putExtra("senha", et_Password2.text.toString())
                    setResult(Activity.RESULT_OK, intent)
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()

                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        it.exception?.message,
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

    }

}
