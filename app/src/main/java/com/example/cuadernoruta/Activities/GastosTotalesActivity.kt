package com.example.cuadernoruta.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.room.Room
import com.example.cuadernoruta.BBDD.AppDataBase
import com.example.cuadernoruta.R
import com.example.cuadernoruta.databinding.ActivityMainBinding

class GastosTotalesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gastos_totales)

        //referencio la base de datos para poder usarla donde me interese
        val db: AppDataBase = Room.databaseBuilder(this, AppDataBase::class.java,"paginasDb").allowMainThreadQueries().build()

        //referencio todas las vistas
        val totalkm = findViewById<TextView>(R.id.tv_totalkm)
        val totalgasolina = findViewById<TextView>(R.id.tv_totalgasolina)
        val totalpeajes = findViewById<TextView>(R.id.tv_totalpeajes)
        val totalpernocta = findViewById<TextView>(R.id.tv_totalpernocta)
        val totalsuper = findViewById<TextView>(R.id.tv_totalsuper)
        val totalrtes = findViewById<TextView>(R.id.tv_totalrtes)
        val totalotros = findViewById<TextView>(R.id.tv_totalotros)
        val totalatracciones = findViewById<TextView>(R.id.tv_totalatracciones)
        val totalotrosocio = findViewById<TextView>(R.id.tv_totalotrosocio)

        //recojo los datos de la bbdd para cada apartado (sumando los totales de cada columna) y se lo asigno
        totalkm.setText(db.paginaDao().gettotalkm().toString() + " km")
        totalgasolina.setText(db.paginaDao().gettotalgasolina().toString() + " €")
        totalpeajes.setText(db.paginaDao().gettotalpeajes().toString()+ " €")
        totalpernocta.setText(db.paginaDao().gettotalpernocta().toString()+ " €")
        totalsuper.setText(db.paginaDao().gettotalsuper().toString()+ " €")
        totalrtes.setText(db.paginaDao().gettotalrtes().toString()+ " €")
        totalotros.setText(db.paginaDao().gettotalotroscompras().toString()+ " €")
        totalatracciones.setText(db.paginaDao().gettotalatracciones().toString()+ " €")
        totalotrosocio.setText(db.paginaDao().gettotalotrosocio().toString()+ " €")

    }
}