package com.example.appgestiondatos.model

import java.io.Serializable

data class Pokemon(
    val nombre: String,
    val url: String,
    val descripcion: String = ""
): Serializable
