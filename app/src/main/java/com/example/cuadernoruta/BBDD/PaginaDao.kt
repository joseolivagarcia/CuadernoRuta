package com.example.cuadernoruta.BBDD

import androidx.room.*
import com.example.cuadernoruta.Models.Pagina

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

    //para actualizar una pagina (actualiza la pagina que recibe por parametro
    @Update
    fun update(pagina: Pagina)

    //creo las funciones que suman los datos de cada columna
    @Query("SELECT SUM(kilometros) FROM Pagina")
    fun gettotalkm(): Float

    @Query("SELECT SUM(gasolina) FROM Pagina")
    fun gettotalgasolina(): Float

    @Query("SELECT SUM(peajes) FROM Pagina")
    fun gettotalpeajes(): Float

    @Query("SELECT SUM(pernocta) FROM Pagina")
    fun gettotalpernocta(): Float

    @Query("SELECT SUM(supermercado) FROM Pagina")
    fun gettotalsuper(): Float

    @Query("SELECT SUM(restaurantes) FROM Pagina")
    fun gettotalrtes(): Float

    @Query("SELECT SUM(otrosCompras) FROM Pagina")
    fun gettotalotroscompras(): Float

    @Query("SELECT SUM(atracciones) FROM Pagina")
    fun gettotalatracciones(): Float

    @Query("SELECT SUM(otrosOcio) FROM Pagina")
    fun gettotalotrosocio(): Float

    @Query("SELECT SUM(gasolina + peajes + pernocta + supermercado + restaurantes + otrosCompras + atracciones + otrosOcio) FROM Pagina")
    fun gettotal(): Float
}