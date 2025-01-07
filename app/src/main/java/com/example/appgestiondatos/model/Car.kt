package com.example.appgestiondatos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tblCar")
data class Car(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "marca")
    val marca: String,
    @ColumnInfo(name = "modelo")
    val modelo: String,
    @ColumnInfo(name = "preferencias")
    val preferencias: String
):Serializable
