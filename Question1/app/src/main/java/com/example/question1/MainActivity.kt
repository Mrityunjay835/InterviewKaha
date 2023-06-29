package com.example.question1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.view.animation.AnimationUtils


class MainActivity : AppCompatActivity() {
    private lateinit var textView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.txt_center)

        textView.setOnClickListener(
            View.OnClickListener {
                val animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
                textView.startAnimation(animation)
            }
        )



    }
}