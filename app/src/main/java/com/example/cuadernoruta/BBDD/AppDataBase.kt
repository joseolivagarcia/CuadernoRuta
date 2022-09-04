package com.example.cuadernoruta.BBDD

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cuadernoruta.Models.Pagina

@Database(
    entities = [Pagina::class],
    version = 1
)
abstract class AppDataBase(): RoomDatabase() {
    abstract fun paginaDao(): PaginaDao
}