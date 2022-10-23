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

    //funcion para eliminar todas las paginas de un viaje
    @Query("Delete From Pagina Where nomviaje = :nomviaje")
    fun deleteAllPaginasByNomviaje(nomviaje: String)

    //hago lo mismo para un insert, al recibir una List puedo insertar una pagina o varias a la vez
    @Insert
    fun insert(pagina: Pagina)

    //creo una funcion para retornar una pagina (la que busque en este caso por id)
    @Query("SELECT * FROM Pagina WHERE id = :id")
    fun getById(id: Int): Pagina

    //funcion para filtrar por el numero de viaje (el del spinner)
    @Query("Select * from Pagina where viaje = :num")
    fun getAllPaginasByNum(num: Int): MutableList<Pagina>

    //funcion para filtrar los viajes por nombre de viaje
    @Query("Select * from Pagina Where nomviaje = :nomviaje")
    fun getAllPaginasByNomviaje(nomviaje: String): MutableList<Pagina>

    //para actualizar una pagina (actualiza la pagina que recibe por parametro
    @Update
    fun update(pagina: Pagina)

    //creo las funciones que suman los datos de cada columna

    @Query("SELECT SUM(kilometros) FROM Pagina Where nomviaje = :viaje")
    fun gettotalkm(viaje: String): Float

    @Query("SELECT SUM(gasolina) FROM Pagina Where nomviaje = :viaje")
    fun gettotalgasolina(viaje: String): Float

    @Query("SELECT SUM(peajes) FROM Pagina Where nomviaje = :viaje")
    fun gettotalpeajes(viaje: String): Float

    @Query("SELECT SUM(pernocta) FROM Pagina Where nomviaje = :viaje")
    fun gettotalpernocta(viaje: String): Float

    @Query("SELECT SUM(supermercado) FROM Pagina Where nomviaje = :viaje")
    fun gettotalsuper(viaje: String): Float

    @Query("SELECT SUM(restaurantes) FROM Pagina Where nomviaje = :viaje")
    fun gettotalrtes(viaje: String): Float

    @Query("SELECT SUM(otrosCompras) FROM Pagina Where nomviaje = :viaje")
    fun gettotalotroscompras(viaje: String): Float

    @Query("SELECT SUM(atracciones) FROM Pagina Where nomviaje = :viaje")
    fun gettotalatracciones(viaje: String): Float

    @Query("SELECT SUM(otrosOcio) FROM Pagina Where nomviaje = :viaje")
    fun gettotalotrosocio(viaje: String): Float

    @Query("SELECT SUM(gasolina + peajes + pernocta + supermercado + restaurantes + otrosCompras + atracciones + otrosOcio) FROM Pagina Where nomviaje = :viaje")
    fun gettotal(viaje: String): Float

    //sentencias para obtener los viajes que hayamos añadido
    @Query("Select * From Viajes")
    fun getAllViajes(): LiveData<List<Viajes>>
    @Insert
    suspend fun insertViaje(viajes: Viajes)
    @Delete
    suspend fun deleteViaje(viajes: Viajes)
    @Query("Delete from Viajes Where nomViaje = :nomviaje")
    suspend fun borrarViaje(nomviaje: String)
}