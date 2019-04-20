package com.ariel.healthdelivery

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private val CADASTRO = 1
    val mAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // verifica se usuario esta conectado
        if(mAuth.currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        btn_Login.setOnClickListener {
            fazerLogin()

        }
        btn_CreateAccount.setOnClickListener{
            var intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent,CADASTRO)
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.

    }


    fun fazerLogin() {
        if (et_Email.text.toString().isNullOrEmpty() || et_Password.text.toString().isNullOrEmpty())   {
            Toast.makeText(
                applicationContext,
                "Digite Algo", Toast.LENGTH_SHORT
            ).show()
            return


        }
        if (!et_Email.text.toString().contains("@")){
            Toast.makeText(
                applicationContext,
                "Digite um email valido", Toast.LENGTH_SHORT
            ).show()
            return
        }
            mAuth.signInWithEmailAndPassword(
                et_Email.text.toString(),
                et_Password.text.toString()
            ).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Email ou senha inválidos", Toast.LENGTH_SHORT
                    ).show()
                }
            })

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CADASTRO -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        et_Email.setText(data?.getStringExtra("email"))
                        et_Password.setText(data?.getStringExtra("senha"))
                    }
                }
            }
        }
    }
}

