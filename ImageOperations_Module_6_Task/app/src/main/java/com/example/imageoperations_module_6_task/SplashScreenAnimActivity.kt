package com.example.imageoperations_module_6_task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.example.imageoperations_module_6_task.databinding.ActivityMainBinding
import com.example.imageoperations_module_6_task.databinding.ActivitySplashScreenAnimBinding

class SplashScreenAnimActivity : AppCompatActivity() {


    private val splashDuration = 5000L

    private lateinit var binding: ActivitySplashScreenAnimBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenAnimBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        val animation = AnimationUtils.loadAnimation(this, R.anim.blink_img)
        binding.splashLogoImageView.startAnimation(animation)


        Handler().postDelayed(Runnable {

            startActivity(Intent(applicationContext,MainActivity::class.java))
        },3000)
    }
}