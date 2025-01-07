package com.example.appgestiondatos.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appgestiondatos.model.Car

@Dao
interface CarDao {
    @Insert
    fun insert(car: Car):Long

    @Update
    fun update(car: Car)

    @Delete
    fun delete(car: Car)

    @Query("SELECT * FROM tblCar order by id")
    fun getCars():LiveData<List<Car>>




}