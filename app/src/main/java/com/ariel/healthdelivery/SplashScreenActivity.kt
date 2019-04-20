package com.ariel.healthdelivery

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar!!.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        load()

    }
    private fun load() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        iv_splash.startAnimation(animation)
        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
        }, 3500)


    }

    override fun onStop() {
        super.onStop()
        finish()
    }

}
