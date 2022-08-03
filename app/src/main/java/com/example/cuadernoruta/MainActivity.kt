package com.example.cuadernoruta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private val listado = listOf<Pagina>(
        Pagina(
            25f,35f,46f
        ),
        Pagina(12f,34f,89f)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
    }

    private fun initRecyclerView() {
       val viewpager = findViewById<ViewPager2>(R.id.viewPager)
        viewpager.adapter = ViewPagerAdapter(listado)
    }
}