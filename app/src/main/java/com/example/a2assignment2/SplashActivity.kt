package com.example.a2assignment2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    //adding timer for splash screen
    private lateinit var timer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //creating timer
        timer = object : CountDownTimer(2000, 1000) {
            override fun onTick(p0: Long) {

            }
            //Splash timer start
            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
        }.start()
        //restart

    }
    //timer cancel when open, close and open again
    override fun onDestroy() {
        super.onDestroy()
    }
}