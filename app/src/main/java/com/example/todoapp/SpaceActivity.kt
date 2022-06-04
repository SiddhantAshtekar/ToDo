package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.style.BackgroundColorSpan
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SpaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space)

        val backgroundImg : ImageView = findViewById(R.id.iv_logo)
        val sideAnimation = AnimationUtils.loadAnimation(this,R.anim.sliding)
        backgroundImg.startAnimation(sideAnimation)
        Handler().postDelayed(
            {
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            },1500)
    }
}