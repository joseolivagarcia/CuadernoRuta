package com.example.cuadernoruta.Activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import com.example.cuadernoruta.Adapters.ViewPagerAdapter
import com.example.cuadernoruta.BBDD.AppDataBase
import com.example.cuadernoruta.Models.Pagina
import com.example.cuadernoruta.R
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

        //agregamos el toolbar del xml
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

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

    //para poner menus en el toolbar sobreescribo estos dos metodos
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mimenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.locomocion -> {
                val intent = Intent(this,GraphLocomocionActivity::class.java)
                startActivity( intent, null)
                return true
            }
            R.id.ocio -> {
                val intent = Intent(this,GraphOcioActivity::class.java)
                startActivity( intent, null)
                return true
            }
            R.id.compras -> {
                val intent = Intent(this,GraphAlimentacionActivity::class.java)
                startActivity( intent, null)
                return true
            }
            R.id.total -> {
                val intent = Intent(this,GraphGastosTotalesActivity::class.java)
                startActivity( intent, null)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //Deactivate back on this Activity
    override fun onBackPressed() {}
}