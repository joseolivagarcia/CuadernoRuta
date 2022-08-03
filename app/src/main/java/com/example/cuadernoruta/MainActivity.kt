package com.example.cuadernoruta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES"))
    private val currentDate = sdf.format(Date())

    private val listado = listOf<Pagina>(
        Pagina(
            currentDate,"Boadilla - Huesca",377f,
            80f,0f,0f,
            120f,0f,0f,
            0f,0f,
            "Viaje tranquilo. Día de ruta"),
        Pagina(
            currentDate,"Huesca - Andorra",395f,
            0f,20f,0f,
            0f,90f,15f,
            0f,0f,
            "Otro día de carretera largo. Por fín en destino..."),
        Pagina(
            currentDate,"NaturPark - Ordino",67f,
            60f,0f,0f,
            0f,0f,20f,
            100f,0f,
            "Día divertido en Naturland. Impresionante el ToboTronc!!!. Ahora en parking de ordino.")

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