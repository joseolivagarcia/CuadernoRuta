package com.example.cuadernoruta.Models

import android.widget.DatePicker
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//esta data class es la clase que contiene las variables que necesitamos para rellenar cada pagina
//Con @Entity hacemos que represente a una tabla de la base de datos
@Entity
data class Pagina(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fecha: String,
    val ruta: String,
    val kilometros: Float,
    val gasolina: Float,
    val peajes: Float,
    val pernocta: Float,
    val supermercado: Float,
    val restaurantes: Float,
    val otrosCompras: Float,
    val atracciones: Float,
    val otrosOcio: Float,
    val comentarios: String,
    val viaje: Int

)
