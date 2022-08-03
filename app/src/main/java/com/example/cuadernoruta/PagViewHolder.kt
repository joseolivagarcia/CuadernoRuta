package com.example.cuadernoruta

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PagViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val icocamper = view.findViewById<ImageView>(R.id.camper)
    val gasolina = view.findViewById<TextView>(R.id.tv_gasolina_datos)
    val peajes = view.findViewById<TextView>(R.id.tv_peajes_datos)
    val pernocta = view.findViewById<TextView>(R.id.tv_pernocta_datos)

    fun render(pagina: Pagina){

        gasolina.text = pagina.gasolina.toString()
        peajes.text = pagina.peajes.toString()
        pernocta.text = pagina.pernocta.toString()

        itemView.setOnClickListener{
            //hacemos clickable la tarjeta(pagina) entera, da igual en que parte pulsemos
            Toast.makeText(gasolina.context,pagina.gasolina.toString(),Toast.LENGTH_LONG).show()
        }

        //podria hacer clickable alguna parte concreta, por ejemplo el icono de la auto
        icocamper.setOnClickListener{
            Toast.makeText(gasolina.context,"has pulsado el icono de la camper",Toast.LENGTH_LONG).show()
        }
    }

}