package com.example.cuadernoruta

import android.view.View
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class PagViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val icocamper = view.findViewById<ImageView>(R.id.camper)

    val fecha = view.findViewById<TextView>(R.id.tv_fecha_datos)
    val ruta = view.findViewById<TextView>(R.id.tv_ruta_datos)
    val km = view.findViewById<TextView>(R.id.tv_km_datos)

    val gasolina = view.findViewById<TextView>(R.id.tv_gasolina_datos)
    val peajes = view.findViewById<TextView>(R.id.tv_peajes_datos)
    val pernocta = view.findViewById<TextView>(R.id.tv_pernocta_datos)

    val supermercados = view.findViewById<TextView>(R.id.tv_super_datos)
    val restaurantes = view.findViewById<TextView>(R.id.tv_rtes_datos)
    val otroscompras = view.findViewById<TextView>(R.id.tv_otros_datos)

    val atracciones = view.findViewById<TextView>(R.id.tv_atracciones_datos)
    val otrosocio = view.findViewById<TextView>(R.id.tv_otrosocio_datos)

    val comentarios = view.findViewById<TextView>(R.id.tv_comentario)



    fun render(pagina: Pagina){

        fecha.text = pagina.fecha
        ruta.text = pagina.ruta
        km.text = pagina.kilometros.toString() + " Km"

        gasolina.text = pagina.gasolina.toString() + " €"
        peajes.text = pagina.peajes.toString()+ " €"
        pernocta.text = pagina.pernocta.toString()+ " €"

        supermercados.text = pagina.supermercado.toString()+ " €"
        restaurantes.text = pagina.restaurantes.toString()+ " €"
        otroscompras.text = pagina.otrosCompras.toString()+ " €"

        atracciones.text = pagina.atracciones.toString()+ " €"
        otrosocio.text = pagina.otrosOcio.toString()+ " €"

        comentarios.text = pagina.comentarios

        itemView.setOnClickListener{
            //hacemos clickable la tarjeta(pagina) entera, da igual en que parte pulsemos
            Toast.makeText(gasolina.context,"Has pulsado la ruta: ${pagina.ruta}",Toast.LENGTH_LONG).show()
        }

        //podria hacer clickable alguna parte concreta, por ejemplo el icono de la auto
        icocamper.setOnClickListener{
            Toast.makeText(gasolina.context,"has pulsado el icono de la camper",Toast.LENGTH_LONG).show()
        }
    }

}