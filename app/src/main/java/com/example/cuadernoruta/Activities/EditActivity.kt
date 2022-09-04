package com.example.cuadernoruta.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.room.Room
import com.example.cuadernoruta.BBDD.AppDataBase
import com.example.cuadernoruta.Models.Pagina
import com.example.cuadernoruta.R

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        //recojo el id que me viene (si es que me viene)
        val paginaid = intent.extras?.getInt("idpagina")
        Toast.makeText(this,"${paginaid.toString()}",Toast.LENGTH_SHORT).show()

        //referencio la base de datos para poder usarla donde me interese
        val db: AppDataBase = Room.databaseBuilder(this,AppDataBase::class.java,"paginasDb").allowMainThreadQueries().build()

        //referencio todas las vistas que voy a usar

        val fecha = findViewById<DatePicker>(R.id.date)
        val ruta = findViewById<EditText>(R.id.et_ruta)
        val kilometros = findViewById<EditText>(R.id.et_km)
        val gasolina = findViewById<EditText>(R.id.et_gasolina)
        val peajes = findViewById<EditText>(R.id.et_peajes)
        val pernocta = findViewById<EditText>(R.id.et_pernocta)
        val supermercado = findViewById<EditText>(R.id.et_supermercado)
        val restaurantes = findViewById<EditText>(R.id.et_rtes)
        val otroscompras = findViewById<EditText>(R.id.et_otros_compras)
        val atracciones = findViewById<EditText>(R.id.et_atracciones)
        val otrosocio = findViewById<EditText>(R.id.et_otros_ocio)
        val comentarios = findViewById<EditText>(R.id.et_comentario)
        val btneditar = findViewById<ImageView>(R.id.icoedit)
        val btnguardar = findViewById<ImageView>(R.id.icoguardar)
        val btnborrar = findViewById<ImageView>(R.id.icoborrar)

        //obtengo de la bbdd la pagina que tiene el id que recibo y relleno los campos con sus datos
        val paginaRecibida = paginaid?.let { db.paginaDao().getById(it) }
        if(paginaid != null){
            btnguardar.isEnabled = false
            //si el id no es nulo, es decir que si traigo una pagina
            if(paginaid >= 0) {

                if(paginaRecibida != null){

                }
                if (paginaRecibida != null) {
                    ruta.setText(paginaRecibida.ruta)
                }
                if (paginaRecibida != null) {
                    kilometros.setText(paginaRecibida.kilometros.toString())
                }
                if (paginaRecibida != null) {
                    gasolina.setText(paginaRecibida.gasolina.toString())
                }
                if (paginaRecibida != null) {
                    peajes.setText(paginaRecibida.peajes.toString())
                }
                if (paginaRecibida != null) {
                    pernocta.setText(paginaRecibida.pernocta.toString())
                }
                if (paginaRecibida != null) {
                    supermercado.setText(paginaRecibida.supermercado.toString())
                }
                if (paginaRecibida != null) {
                    restaurantes.setText(paginaRecibida.restaurantes.toString())
                }
                if (paginaRecibida != null) {
                    otroscompras.setText(paginaRecibida.otrosCompras.toString())
                }
                if (paginaRecibida != null) {
                    atracciones.setText(paginaRecibida.atracciones.toString())
                }
                if (paginaRecibida != null) {
                    atracciones.setText(paginaRecibida.atracciones.toString())
                }
                if (paginaRecibida != null) {
                    otrosocio.setText(paginaRecibida.otrosOcio.toString())
                }
                if (paginaRecibida != null) {
                    comentarios.setText(paginaRecibida.comentarios)
                }
                }
            }else {
            btneditar.isEnabled = false //si estoy para crear una pagina nueva este boton no tendra sentido (solo el de guardar)
            btnborrar.isEnabled = false //si estoy para crear una pagina no tiene sentido borrar
        }

        //doy funcionalidad al boton de guardar una pagina
        btnguardar.setOnClickListener {
            //obtengo todos los textos de los campos que haya rellenado
            val dia = fecha.dayOfMonth
            val mes = fecha.month
            val year = fecha.year
            val datosfecha = "$dia / $mes / $year"
            val datosruta = ruta.text.toString()
            val datoskm = kilometros.text.toString()
            val datosgas = gasolina.text.toString()
            val datospeajes = peajes.text.toString()
            val datospernocta = pernocta.text.toString()
            val datossuper = supermercado.text.toString()
            val datosrtes = restaurantes.text.toString()
            val datosotroscompras = otroscompras.text.toString()
            val datosatracc = atracciones.text.toString()
            val datosotrosocio = otrosocio.text.toString()
            val datoscoment = comentarios.text.toString()

            //creo una nueva pagina y la guardo en la bbdd
            val newPagina = Pagina(0,
                datosfecha,
                datosruta,
                datoskm.toFloat(),
                datosgas.toFloat(),
                datospeajes.toFloat(),
                datospernocta.toFloat(),
                datossuper.toFloat(),
                datosrtes.toFloat(),
                datosotroscompras.toFloat(),
                datosatracc.toFloat(),
                datosotrosocio.toFloat(),
                datoscoment
            )

            db.paginaDao().insert(newPagina)

            //vuelvo a la actividad ppal para comprobar si se ha almacenado la hoja de gasto
            val intent = Intent(this,MainActivity::class.java)
            startActivity( intent, null)

            Toast.makeText(this,"Página guardada correctamente",Toast.LENGTH_SHORT).show()
        }

        btneditar.setOnClickListener {
            //practicamente es lo mismo que el boton de guardar solo que actualizamos en la bbdd

            val dia = fecha.dayOfMonth
            val mes = fecha.month
            val year = fecha.year
            val datosfecha = "$dia / $mes / $year"
            val datosruta = ruta.text.toString()
            val datoskm = kilometros.text.toString()
            val datosgas = gasolina.text.toString()
            val datospeajes = peajes.text.toString()
            val datospernocta = pernocta.text.toString()
            val datossuper = supermercado.text.toString()
            val datosrtes = restaurantes.text.toString()
            val datosotroscompras = otroscompras.text.toString()
            val datosatracc = atracciones.text.toString()
            val datosotrosocio = otrosocio.text.toString()
            val datoscoment = comentarios.text.toString()

            //creo una nueva pagina (la misma con los datos actualizados) y la guardo en la bbdd
            val editPagina = paginaid?.let {
                    it1 -> Pagina(
                it1,datosfecha,
                datosruta,
                datoskm.toFloat(),
                datosgas.toFloat(),
                datospeajes.toFloat(),
                datospernocta.toFloat(),
                datossuper.toFloat(),
                datosrtes.toFloat(),
                datosotroscompras.toFloat(),
                datosatracc.toFloat(),
                datosotrosocio.toFloat(),
                datoscoment) }

            if (editPagina != null) {
                db.paginaDao().update(editPagina)
            }

            //vuelvo a la actividad ppal para comprobar si se ha almacenado la hoja de gasto
            val intent = Intent(this,MainActivity::class.java)
            startActivity( intent, null)

            Toast.makeText(this,"Página editada correctamente",Toast.LENGTH_SHORT).show()
        }

        btnborrar.setOnClickListener {
            var pag: Pagina? = null
            if (paginaid != null) {
                pag = db.paginaDao().getById(paginaid)
            }
            if (pag != null) {
                db.paginaDao().delete(pag)
            }

            //vuelvo a la actividad ppal para comprobar si se ha almacenado la hoja de gasto
            val intent = Intent(this,MainActivity::class.java)
            startActivity( intent, null)

            Toast.makeText(this,"Página $paginaid borrada correctamente",Toast.LENGTH_SHORT).show()
        }

    }

}