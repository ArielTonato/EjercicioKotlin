package com.example.appgestiondatos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appgestiondatos.model.Car

@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    //Se define el dao  a utilizar
    abstract  fun carDao(): CarDao
    //manejar una instancia de la base de datos
    companion object{
        var instance: AppDataBase? = null
        fun getInstance(context:Context):AppDataBase{
            if (instance == null){
                instance = Room.databaseBuilder(context,
                    AppDataBase::class.java,
                    "dbCar"
                ).build()
            }
            return instance as AppDataBase
        }
    }
}