package com.example.cuadernoruta.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.cuadernoruta.Adapters.ViewPagerAdapter
import com.example.cuadernoruta.BBDD.AppDataBase
import com.example.cuadernoruta.Models.Pagina
import com.example.cuadernoruta.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    //variable para poder usar Bindind(nuevo sistema para referenciar vistas)
    private lateinit var binding: ActivityMainBinding

    private val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES"))
    private val currentDate = sdf.format(Date())

    //creo una var de tipo mutableList para guardar todas las paginas que vaya creando
    var listadoPaginas: MutableList<Pagina> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inicializo binding para que funcione correctamente
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //referencio la base de datos para poder usarla donde me interese
        val db: AppDataBase = Room.databaseBuilder(this,AppDataBase::class.java,"paginasDb").allowMainThreadQueries().build()

        //inicializo lista en la que guardo lo que recoja de la base de datos
        listadoPaginas = db.paginaDao().getAll()
        //Toast.makeText(this,"${listadoPaginas.size.toString()}",Toast.LENGTH_SHORT).show()
        //referencio el boton + y lo inicializo
        val fab = binding.fab
        fab.setOnClickListener {
            //lo que hace el boton es llavarnos a la actividad de editar(sin datos) para crear una nueva pagina
            val intent = Intent(this,EditActivity::class.java)
            startActivity(intent)
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val viewpager = binding.viewPager
        viewpager.adapter = ViewPagerAdapter(listadoPaginas)
    }

    //Deactivate back on this Activity
    override fun onBackPressed() {}
}