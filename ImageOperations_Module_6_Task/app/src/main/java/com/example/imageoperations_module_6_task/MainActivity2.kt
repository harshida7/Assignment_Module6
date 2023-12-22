package com.example.imageoperations_module_6_task


import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.imageoperations_module_6_task.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    lateinit var frameAnimation: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.imageview.setBackgroundResource(R.drawable.animation)


        frameAnimation = binding.imageview.getBackground() as AnimationDrawable

        binding.BTNStart.setOnClickListener {

            frameAnimation.start()
        }

        binding.BTNStop.setOnClickListener {

            frameAnimation.stop()
        }

    }
}