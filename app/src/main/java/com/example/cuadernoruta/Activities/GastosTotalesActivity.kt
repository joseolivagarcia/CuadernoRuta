package com.example.cuadernoruta.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cuadernoruta.R
import com.example.cuadernoruta.databinding.ActivityMainBinding

class GastosTotalesActivity : AppCompatActivity() {

    //variable para poder usar Bindind(nuevo sistema para referenciar vistas)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inicializo binding para que funcione correctamente
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}