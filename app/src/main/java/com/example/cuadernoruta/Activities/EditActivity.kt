package com.example.cuadernoruta.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.room.Room
import com.example.cuadernoruta.BBDD.AppDataBase
import com.example.cuadernoruta.Models.Pagina
import com.example.cuadernoruta.R

class EditActivity : AppCompatActivity() {

    lateinit var ruta: EditText
    lateinit var kilometros: EditText
    lateinit var gasolina: EditText
    lateinit var peajes: EditText
    lateinit var pernocta: EditText
    lateinit var supermercado: EditText
    lateinit var restaurantes: EditText
    lateinit var otroscompras: EditText
    lateinit var atracciones: EditText
    lateinit var otrosocio: EditText
    lateinit var comentarios: EditText

    lateinit var datosruta: String
    lateinit var datoskm: String
    lateinit var datosgas: String
    lateinit var datospeajes: String
    lateinit var datospernocta: String
    lateinit var datossuper: String
    lateinit var datosrtes: String
    lateinit var datosotroscompras: String
    lateinit var datosatracc: String
    lateinit var datosotrosocio: String
    lateinit var datoscoment: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        //para poner la flecha atras en el toolbar
        //al final sobreescribo el metodo onOptionsItemSelected
        /*
        * para que la flecha sea blanca añado un item al style de themes
        * con colorControlNormal*/
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setDisplayShowHomeEnabled(true)

        //recojo el id que me viene (si es que me viene)
        val paginaid = intent.extras?.getInt("idpagina")
        //Toast.makeText(this,"${paginaid.toString()}",Toast.LENGTH_SHORT).show()

        //referencio la base de datos para poder usarla donde me interese
        val db: AppDataBase = Room.databaseBuilder(this,AppDataBase::class.java,"paginasDb").allowMainThreadQueries().build()

        //referencio todas las vistas que voy a usar

        val fecha = findViewById<DatePicker>(R.id.date)
        ruta = findViewById(R.id.et_ruta)
        kilometros = findViewById(R.id.et_km)
        gasolina = findViewById(R.id.et_gasolina)
        peajes = findViewById(R.id.et_peajes)
        pernocta = findViewById(R.id.et_pernocta)
        supermercado = findViewById(R.id.et_supermercado)
        restaurantes = findViewById(R.id.et_rtes)
        otroscompras = findViewById(R.id.et_otros_compras)
        atracciones = findViewById(R.id.et_atracciones)
        otrosocio = findViewById(R.id.et_otros_ocio)
        comentarios = findViewById(R.id.et_comentario)
        val btneditar = findViewById<ImageView>(R.id.icoedit)
        val btnguardar = findViewById<ImageView>(R.id.icoguardar)

        //obtengo de la bbdd la pagina que tiene el id que recibo y relleno los campos con sus datos
        val paginaRecibida = paginaid?.let { db.paginaDao().getById(it) }
        if(paginaid != null){
            btnguardar.isEnabled = false
            //si el id no es nulo, es decir que si traigo una pagina
            if(paginaid >= 0) {

                if (paginaRecibida != null) {
                    ruta.setText(paginaRecibida.ruta)
                    kilometros.setText(paginaRecibida.kilometros.toString())
                    gasolina.setText(paginaRecibida.gasolina.toString())
                    peajes.setText(paginaRecibida.peajes.toString())
                    pernocta.setText(paginaRecibida.pernocta.toString())
                    supermercado.setText(paginaRecibida.supermercado.toString())
                    restaurantes.setText(paginaRecibida.restaurantes.toString())
                    otroscompras.setText(paginaRecibida.otrosCompras.toString())
                    atracciones.setText(paginaRecibida.atracciones.toString())
                    atracciones.setText(paginaRecibida.atracciones.toString())
                    otrosocio.setText(paginaRecibida.otrosOcio.toString())
                    comentarios.setText(paginaRecibida.comentarios)
                }
                }
            }else {
            btneditar.isEnabled = false //si estoy para crear una pagina nueva este boton no tendra sentido (solo el de guardar)
        }

        //doy funcionalidad al boton de guardar una pagina
        btnguardar.setOnClickListener {
            //obtengo todos los textos de los campos que haya rellenado
            obtenerCampos()
            val dia = fecha.dayOfMonth
            val mes = fecha.month
            val year = fecha.year
            val datosfecha = "$dia / $mes / $year"

            //me aseguro que si algun campo no esta relleno le asigno un valor

            if (datosruta.equals("")){
                datosruta = "Sin ruta definida"
            }
            if (datoskm .equals("")){
                datoskm = "0"
            }
            if (datosgas.equals("")){
                datosgas = "0"
            }
            if (datospeajes.equals("")){
                datospeajes = "0"
            }
            if (datospernocta.equals("")){
                datospernocta = "0"
            }
            if (datossuper.equals("")){
                datossuper = "0"
            }
            if (datosrtes.equals("")){
                datosrtes = "0"
            }
            if (datosotroscompras.equals("")){
                datosotroscompras = "0"
            }
            if (datosatracc.equals("")){
                datosatracc = "0"
            }
            if (datosotrosocio.equals("")){
                datosotrosocio = "0"
            }
            if (datoscoment.equals("")){
                datoscoment = "Sin comentarios"
            }

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
            //meto la accion en un cuadro de dialogo para confirmar o cancelar
            val dialog = AlertDialog.Builder(this)
                .setMessage("Quieres editar ésta página?")
                .setNegativeButton("NO"){
                    view, _ -> view.dismiss()
                }
                .setPositiveButton("SI"){
                    view, _ -> view.dismiss()
                    //practicamente es lo mismo que el boton de guardar solo que actualizamos en la bbdd
                    obtenerCampos()
                    val dia = fecha.dayOfMonth
                    val mes = fecha.month
                    val year = fecha.year
                    val datosfecha = "$dia / $mes / $year"

                    //me aseguro que si algun campo no esta relleno le asigno un valor

                    if (datosruta.equals("")){
                        datosruta = "Sin ruta definida"
                    }
                    if (datoskm .equals("")){
                        datoskm = "0"
                    }
                    if (datosgas.equals("")){
                        datosgas = "0"
                    }
                    if (datospeajes.equals("")){
                        datospeajes = "0"
                    }
                    if (datospernocta.equals("")){
                        datospernocta = "0"
                    }
                    if (datossuper.equals("")){
                        datossuper = "0"
                    }
                    if (datosrtes.equals("")){
                        datosrtes = "0"
                    }
                    if (datosotroscompras.equals("")){
                        datosotroscompras = "0"
                    }
                    if (datosatracc.equals("")){
                        datosatracc = "0"
                    }
                    if (datosotrosocio.equals("")){
                        datosotrosocio = "0"
                    }
                    if (datoscoment.equals("")){
                        datoscoment = "Sin comentarios"
                    }

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
                .setCancelable(false)
                .create()

            dialog.show()
        }
    }

    private fun obtenerCampos() {
        datosruta = ruta.text.toString()
        datoskm = kilometros.text.toString()
        datosgas = gasolina.text.toString()
        datospeajes = peajes.text.toString()
        datospernocta = pernocta.text.toString()
        datossuper = supermercado.text.toString()
        datosrtes = restaurantes.text.toString()
        datosotroscompras = otroscompras.text.toString()
        datosatracc = atracciones.text.toString()
        datosotrosocio = otrosocio.text.toString()
        datoscoment = comentarios.text.toString()

    }

    public override fun  onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home)
            finish(); // close this activity and return to preview activity (if there is any)

        return super.onOptionsItemSelected(item);
    }


}