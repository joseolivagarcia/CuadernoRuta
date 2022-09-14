package com.example.cuadernoruta.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Viajes(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nomViaje: String
)
