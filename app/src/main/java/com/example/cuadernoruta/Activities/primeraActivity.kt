package com.example.cuadernoruta.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.example.cuadernoruta.BBDD.AppDataBase
import com.example.cuadernoruta.Models.Viajes
import com.example.cuadernoruta.R

class primeraActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {

    var listaViajesSpinner: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primera)
        //meto un primer elemento para que se muestre algo por defecto
        listaViajesSpinner.add("Lista de viajes")

        //referencio la base de datos para poder usarla donde me interese
        val db: AppDataBase = Room.databaseBuilder(this, AppDataBase::class.java,"paginasDb").allowMainThreadQueries().build()
        //y relleno el spinner con los datos que obtenga de ella
        val viajes:MutableList<Viajes> = db.paginaDao().getAllViajes()
        for(v in viajes){
            listaViajesSpinner.add(v.nomViaje)
        }
        val sp = findViewById<Spinner>(R.id.spinner)
        sp.onItemSelectedListener = this

        val btncrear = findViewById<TextView>(R.id.tvcrear)

        btncrear.setOnClickListener {
            //creo un alert dialog para introducir el viaje que queramos crear y añadir a la lista
            val inflater = layoutInflater //para pasarle el layout al dialog
            val dialoglayout = inflater.inflate(R.layout.dailog,null) //paso el xml al inflater
            val textodialog = dialoglayout.findViewById<EditText>(R.id.ettituloviaje) //para poder capturar el texto que escribamos
            val buildialog = AlertDialog.Builder(this) //creamos el alertdialog
            buildialog.setTitle("Viajes")
            buildialog.setView(dialoglayout) //pasamos el layout al alertdialog
            //creamos los botones del alert
            buildialog.setPositiveButton("Añadir"){
                    dialog, _ -> dialog.dismiss()
                //añadimos el viaje a la bbdd
                val newViaje = Viajes(0,textodialog.text.toString())
                db.paginaDao().insertViaje(newViaje)
            }
            buildialog.setNegativeButton("Cancelar"){
                    dialog, _ -> dialog.dismiss()
            }
            buildialog.show() //mostramos el alertdialog

            /*
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

             */
        }

        //creo el adapter para el spinner y se lo adapto
        val spadapter: ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_spinner_item,listaViajesSpinner)
        spadapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1)
        sp.adapter = spadapter
        sp.setSelection(0)

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        if(position != 0) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("viaje", position)
            startActivity(intent)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    //Deactivate back on this Activity
    override fun onBackPressed() {}
}