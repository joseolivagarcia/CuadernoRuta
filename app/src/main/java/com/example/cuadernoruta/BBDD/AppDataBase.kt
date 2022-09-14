package com.example.cuadernoruta.BBDD

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cuadernoruta.Models.Pagina
import com.example.cuadernoruta.Models.Viajes

@Database(
    entities = [Pagina::class, Viajes::class],
    version = 3
)
abstract class AppDataBase(): RoomDatabase() {
    abstract fun paginaDao(): PaginaDao
}