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
}