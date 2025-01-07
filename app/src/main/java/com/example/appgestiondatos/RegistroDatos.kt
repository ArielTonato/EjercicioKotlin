package com.example.appgestiondatos

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appgestiondatos.database.AppDataBase
import com.example.appgestiondatos.databinding.ActivityRegistroDatosBinding
import com.example.appgestiondatos.model.Car
import com.example.appgestiondatos.utils.Constants
import java.util.concurrent.Executors

class RegistroDatos : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroDatosBinding
    private val appDatabase:AppDataBase by lazy { AppDataBase.getInstance(this) }
    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegistroDatosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initializeData()
        handleEvents()
    }

    private fun handleEvents() {
        with(binding){
            btnRegistrar.setOnClickListener {
                val marca = edtMarca.text.toString()
                val modelo = edtModelo.text.toString()
                val preferencias = edtPreferencias.text.toString()
                if(id == 0) add(Car(0, marca, modelo, preferencias))
                else edit(Car(id, marca, modelo, preferencias))
            }
        }
    }

    private fun add(car: Car)
    {
        Executors.newSingleThreadExecutor().execute {
            appDatabase.carDao().insert(car)
            runOnUiThread {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun edit(car: Car){
        Executors.newSingleThreadExecutor().execute {
            appDatabase.carDao().update(car)
            runOnUiThread {
                Toast.makeText(this, "Actualización exitosa", Toast.LENGTH_SHORT).show()
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun initializeData() {
        val bundle = intent.extras
        bundle?.let {
            val car = bundle.getSerializable(Constants.KEY_CAR) as Car
            with(binding){
                btnRegistrar.setText("Actualizar")
                id = car.id
                edtMarca.setText(car.marca)
                edtModelo.setText(car.modelo)
                edtPreferencias.setText(car.preferencias)
            }
        }?:kotlin.run {
            with(binding){
                btnRegistrar.setText("Registrar")
                edtMarca.setText("")
                edtModelo.setText("")
                edtPreferencias.setText("")
            }
        }
        binding.edtMarca.requestFocus()
    }
}