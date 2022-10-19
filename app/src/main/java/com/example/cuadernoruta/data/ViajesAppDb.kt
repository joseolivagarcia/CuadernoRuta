package com.example.cuadernoruta.data

import android.app.Application
import androidx.room.Room
import com.example.cuadernoruta.BBDD.AppDataBase

/*clase que se encarga de iniciar la bbdd al iniciar la app.
en el manifest tiene que tener su referencia
en aplication asi se iniciara nada mas empezar la app
*/

class ViajesAppDb: Application() {

    //creamos un objeto companion para poder acceder a lo que hay dentro y asi al resto de funciones de la clase
    companion object{
        lateinit var db: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this,AppDataBase::class.java,"paginasDb").build()
    }

}