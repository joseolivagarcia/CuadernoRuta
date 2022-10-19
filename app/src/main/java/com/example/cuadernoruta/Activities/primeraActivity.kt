package com.example.cuadernoruta.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.cuadernoruta.BBDD.AppDataBase
import com.example.cuadernoruta.Models.Viajes
import com.example.cuadernoruta.R
import com.example.cuadernoruta.data.ViajesAppDb.Companion.db
import com.example.cuadernoruta.databinding.ActivityPrimeraBinding
import com.example.cuadernoruta.viewmodel.PrimeraViewModel

class primeraActivity : AppCompatActivity() {

    lateinit var binding: ActivityPrimeraBinding
    lateinit var viewmodel: PrimeraViewModel //referencio mi ViewModel
    val listaViajesSp: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrimeraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel =
            ViewModelProvider(this).get(PrimeraViewModel::class.java) //inicializo mi viewmodel
        val spinner = binding.spinner
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, listaViajesSp)
        spinner.adapter = adapter

        val btncrear = binding.tvcrear
        btncrear.setOnClickListener {
            //creo un alert dialog para introducir el viaje que queramos crear y añadir a la lista
            val inflater = layoutInflater //para pasarle el layout al dialog
            val dialoglayout = inflater.inflate(R.layout.dailog, null) //paso el xml al inflater
            val textodialog =
                dialoglayout.findViewById<EditText>(R.id.ettituloviaje) //para poder capturar el texto que escribamos
            val buildialog = AlertDialog.Builder(this) //creamos el alertdialog
            buildialog.setTitle("Crear Viaje")
            buildialog.setView(dialoglayout) //pasamos el layout al alertdialog
            //creamos los botones del alert
            buildialog.setPositiveButton("Añadir") { dialog, _ ->
                dialog.dismiss()
                //añadimos el viaje a la bbdd
                val newViaje = Viajes(0, textodialog.text.toString())
                viewmodel.guardarViaje(newViaje)
                spinner.setSelection(0)
            }
            buildialog.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            buildialog.show() //mostramos el alertdialog

            /*
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
             */
        }
        val btneliminar = binding.tveliminar
        btneliminar.setOnClickListener {
            //creo un alert dialog para introducir el viaje que queramos eliminar de la lista
            val inflater = layoutInflater //para pasarle el layout al dialog
            val dialoglayout = inflater.inflate(R.layout.dailog, null) //paso el xml al inflater
            val textodialog =
                dialoglayout.findViewById<EditText>(R.id.ettituloviaje) //para poder capturar el texto que escribamos
            val buildialog = AlertDialog.Builder(this) //creamos el alertdialog
            buildialog.setTitle("Eliminar Viaje")
            buildialog.setView(dialoglayout) //pasamos el layout al alertdialog
            //creamos los botones del alert
            buildialog.setPositiveButton("Eliminar") { dialog, _ ->
                dialog.dismiss()
                //eliminamos el viaje a la bbdd
                viewmodel.borrarViaje(textodialog.text.toString())
                Toast.makeText(this,"Has borrado ${textodialog.text}",Toast.LENGTH_SHORT).show()
                spinner.setSelection(0)
            }
            buildialog.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            buildialog.show() //mostramos el alertdialog

            /*
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
             */
        }

        viewmodel.viajesList.observe(this, Observer { list ->
            list?.let {
                //actualizamos la lista
                listaViajesSp.clear()
                listaViajesSp.add(0, "Viajes")
                for (v in list) {
                    listaViajesSp.add(v.nomViaje)
                    adapter.notifyDataSetChanged()
                }
            }
        })

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                if (position != 0) {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.putExtra("viaje", position)
                    startActivity(intent)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    //Deactivate back on this Activity
    override fun onBackPressed() {}
}