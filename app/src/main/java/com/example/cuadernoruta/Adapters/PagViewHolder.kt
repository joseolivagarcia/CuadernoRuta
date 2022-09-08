package com.example.cuadernoruta.Adapters

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.cuadernoruta.Activities.*
import com.example.cuadernoruta.BBDD.AppDataBase
import com.example.cuadernoruta.Models.Pagina
import com.example.cuadernoruta.R

class PagViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val icoedit = view.findViewById<ImageView>(R.id.icoedit)
    val icodelete = view.findViewById<ImageView>(R.id.icodelete)
    val linearLocomocion = view.findViewById<LinearLayout>(R.id.idlocomocion)
    val linearAlimen = view.findViewById<LinearLayout>(R.id.idaliment)
    val linearOcio = view.findViewById<LinearLayout>(R.id.idocio)

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

        /*
        itemView.setOnClickListener{
            //hacemos clickable la tarjeta(pagina) entera, da igual en que parte pulsemos
            Toast.makeText(gasolina.context,"Has pulsado la ruta: ${pagina.ruta}",Toast.LENGTH_LONG).show()
        }

         */

        //Hago clicable cada seccion que mostrará las gráficas si las pulso
        linearLocomocion.setOnClickListener {
            val intent = Intent(gasolina.context, GraphLocomocionActivity::class.java)
            gasolina.context.startActivity(intent,null)
        }
        linearAlimen.setOnClickListener {
            val intent = Intent(gasolina.context, GraphAlimentacionActivity::class.java)
            gasolina.context.startActivity(intent,null)
        }
        linearOcio.setOnClickListener {
            val intent = Intent(gasolina.context, GraphOcioActivity::class.java)
            gasolina.context.startActivity(intent,null)
        }



        /*podria hacer clickable alguna parte concreta, por ejemplo el icono de editar y de eliminar
        En este caso como voy a editar una pagina ya creada, tengo que pasarle el id, asi en la actividad
        de editar puedo cargar todos los datos de esa tarjeta y cambiarlos a mi gusto.
         */
        icoedit.setOnClickListener{
            val intent = Intent(gasolina.context, EditActivity::class.java)
            intent.putExtra("idpagina", pagina.id)
            gasolina.context.startActivity(intent,null)


        }

        icodelete.setOnClickListener{
            val dialog = AlertDialog.Builder(gasolina.context)
                .setMessage("Quieres eliminar ésta página?")
                .setNegativeButton("NO"){
                        view, _ -> view.dismiss()
                }
                .setPositiveButton("SI") { view, _ ->
                    view.dismiss()
                    //referencio la base de datos para poder usarla donde me interese
                    val db: AppDataBase = Room.databaseBuilder(gasolina.context,AppDataBase::class.java,"paginasDb").allowMainThreadQueries().build()
                    val paginaABorrar = db.paginaDao().getById(pagina.id)
                    db.paginaDao().delete(paginaABorrar)
                    //vuelvo a la actividad ppal para comprobar si se ha almacenado la hoja de gasto
                    val intent = Intent(gasolina.context, MainActivity::class.java)
                    gasolina.context.startActivity( intent, null)
                    Toast.makeText(gasolina.context,"Pagina eliminada",Toast.LENGTH_SHORT).show()
                }
                .setCancelable(false)
                .create()

            dialog.show()

        }
    }

}