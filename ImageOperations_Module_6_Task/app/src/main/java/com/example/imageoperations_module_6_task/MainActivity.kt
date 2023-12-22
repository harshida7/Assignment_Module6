package com.example.imageoperations_module_6_task



import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.imageoperations_module_6_task.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.BTNblink.setOnClickListener{

            val animation: Animation
            animation = AnimationUtils.loadAnimation(applicationContext, R.anim.blink_img)
            binding.imageview.startAnimation(animation)
        }

        binding.BTNrotate.setOnClickListener {
            val animation: Animation
            animation = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate_img)
            binding.imageview.startAnimation(animation)
        }


        binding.BTNfadein.setOnClickListener{

            val animation: Animation
            animation = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in_img)
            binding.imageview.startAnimation(animation)
        }

        binding.BTNfadeout.setOnClickListener{
            val animation: Animation
            animation = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_out_img)
            binding.imageview.startAnimation(animation)

        }
        binding.BTNmove.setOnClickListener{

            val animation: Animation
            animation = AnimationUtils.loadAnimation(applicationContext, R.anim.move_img)
            binding.imageview.startAnimation(animation)
        }
        binding.BTNslide.setOnClickListener{

            val animation: Animation
            animation = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_img)
            binding.imageview.startAnimation(animation)
        }
        binding.BTNzoomin.setOnClickListener{

            val animation: Animation
            animation = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_in_img)
            binding.imageview.startAnimation(animation)
        }
        binding.BTNzoomout.setOnClickListener{
            val animation: Animation
            animation = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_out_img)
            binding.imageview.startAnimation(animation)

        }



        binding.BTNstop.setOnClickListener {

            binding.imageview.clearAnimation()
        }





        binding.BTNnextActivity.setOnClickListener {

            startActivity(Intent(applicationContext,MainActivity2::class.java))
        }
    }
}
