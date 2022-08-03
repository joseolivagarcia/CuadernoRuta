package com.example.cuadernoruta

import android.widget.DatePicker
import java.util.*

//esta data class es la clase que contiene las variables que necesitamos para rellenar cada pagina

data class Pagina(
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
    val comentarios: String

)
