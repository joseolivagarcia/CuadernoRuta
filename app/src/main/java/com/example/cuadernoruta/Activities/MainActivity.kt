package com.example.cuadernoruta.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cuadernoruta.Adapters.ViewPagerAdapter
import com.example.cuadernoruta.Models.Pagina
import com.example.cuadernoruta.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    //variable para poder usar Bindind(nuevo sistema para referenciar vistas)
    private lateinit var binding: ActivityMainBinding

    private val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES"))
    private val currentDate = sdf.format(Date())

    //creo una lista con páginas precreadas para probar
    private val listado = listOf<Pagina>(
        Pagina(
            0,
            currentDate,
            "Boadilla - Huesca",
            377f,
            80f,
            0f,
            0f,
            120f,
            0f,
            0f,
            0f,
            0f,
            "Viaje tranquilo. Día de ruta"),
        Pagina(0,
            currentDate,"Huesca - Andorra",395f,
            0f,20f,0f,
            0f,90f,15f,
            0f,0f,
            "Otro día de carretera largo. Por fín en destino..."),
        Pagina(0,
            currentDate,"NaturPark - Ordino",67f,
            60f,0f,0f,
            0f,0f,20f,
            100f,0f,
            "Día divertido en Naturland. Impresionante el ToboTronc!!!. Ahora en parking de ordino.")

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inicializo binding para que funcione correctamente
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        //referencio el boton + y lo inicializo
        val fab = binding.fab
        fab.setOnClickListener {
            //lo que hace el boton es llavarnos a la actividad de editar(sin datos) para crear una nueva pagina
            val intent = Intent(this,EditActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
       val viewpager = binding.viewPager
        viewpager.adapter = ViewPagerAdapter(listado)
    }

    //Deactivate back on this Activity
    override fun onBackPressed() {}
}