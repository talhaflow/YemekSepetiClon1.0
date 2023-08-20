package com.talhakara.yemeksepeti.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.constraintlayout.widget.ConstraintLayout
import com.talhakara.yemeksepeti.R

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)


        Handler().postDelayed({
            val intent = Intent(this@IntroActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // Bu aktiviteyi kapat
        }, 1000) // 1000 milisaniye (1 saniye) gecikme
    }
}
