package com.example.cuadernoruta.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.cuadernoruta.R

class primeraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primera)

        val btncrear = findViewById<TextView>(R.id.tvcrear)
        btncrear.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    //Deactivate back on this Activity
    override fun onBackPressed() {}
}