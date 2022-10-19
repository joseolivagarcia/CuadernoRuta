package com.example.cuadernoruta.BBDD

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cuadernoruta.Models.Pagina
import com.example.cuadernoruta.Models.Viajes

@Dao
interface PaginaDao {
    //creo una funcion que me va a devolver todos los datos de la bbdd (todas las paginas)
    @Query("SELECT * FROM Pagina")
    fun getAll():MutableList<Pagina>

    //funcion para eliminar
    @Delete
    fun delete(pagina: Pagina)

    //hago lo mismo para un insert, al recibir una List puedo insertar una pagina o varias a la vez
    @Insert
    fun insert(pagina: Pagina)

    //creo una funcion para retornar una pagina (la que busque en este caso por id)
    @Query("SELECT * FROM Pagina WHERE id = :id")
    fun getById(id: Int): Pagina

    //funcion para filtrar por el numero de viaje (el del spinner)
    @Query("Select * from Pagina where viaje = :num")
    fun getAllPaginasByNum(num: Int): MutableList<Pagina>

    //para actualizar una pagina (actualiza la pagina que recibe por parametro
    @Update
    fun update(pagina: Pagina)

    //creo las funciones que suman los datos de cada columna
    @Query("SELECT SUM(kilometros) FROM Pagina Where viaje = :viaje")
    fun gettotalkm(viaje: Int): Float

    @Query("SELECT SUM(gasolina) FROM Pagina Where viaje = :viaje")
    fun gettotalgasolina(viaje: Int): Float

    @Query("SELECT SUM(peajes) FROM Pagina Where viaje = :viaje")
    fun gettotalpeajes(viaje: Int): Float

    @Query("SELECT SUM(pernocta) FROM Pagina Where viaje = :viaje")
    fun gettotalpernocta(viaje: Int): Float

    @Query("SELECT SUM(supermercado) FROM Pagina Where viaje = :viaje")
    fun gettotalsuper(viaje: Int): Float

    @Query("SELECT SUM(restaurantes) FROM Pagina Where viaje = :viaje")
    fun gettotalrtes(viaje: Int): Float

    @Query("SELECT SUM(otrosCompras) FROM Pagina Where viaje = :viaje")
    fun gettotalotroscompras(viaje: Int): Float

    @Query("SELECT SUM(atracciones) FROM Pagina Where viaje = :viaje")
    fun gettotalatracciones(viaje: Int): Float

    @Query("SELECT SUM(otrosOcio) FROM Pagina Where viaje = :viaje")
    fun gettotalotrosocio(viaje: Int): Float

    @Query("SELECT SUM(gasolina + peajes + pernocta + supermercado + restaurantes + otrosCompras + atracciones + otrosOcio) FROM Pagina Where viaje = :viaje")
    fun gettotal(viaje: Int): Float

    //sentencias para obtener los viajes que hayamos añadido
    @Query("Select * From Viajes")
    fun getAllViajes(): LiveData<List<Viajes>>
    @Insert
    suspend fun insertViaje(viajes: Viajes)
}