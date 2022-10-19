package com.example.cuadernoruta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuadernoruta.Models.Viajes
import com.example.cuadernoruta.data.ViajesAppDb.Companion.db
import kotlinx.coroutines.launch

class PrimeraViewModel: ViewModel() {

    var viajesList: LiveData<List<Viajes>>
    init {
        viajesList = db.paginaDao().getAllViajes()
    }


    fun guardarViaje(viaje: Viajes){
        viewModelScope.launch {
            db.paginaDao().insertViaje(viaje)
        }
    }

    fun deleteViaje(viaje: Viajes){
        viewModelScope.launch {
            db.paginaDao().deleteViaje(viaje)
        }
    }

    fun borrarViaje(nomviaje: String){
        viewModelScope.launch {
            db.paginaDao().borrarViaje(nomviaje)
        }
    }
}