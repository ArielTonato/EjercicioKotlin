package com.example.appgestiondatos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appgestiondatos.database.AppDataBase
import com.example.appgestiondatos.databinding.ActivityListadoDatosBinding
import com.example.appgestiondatos.utils.Constants
import java.util.concurrent.Executors

class ListadoDatos : AppCompatActivity() {
    private lateinit var binding: ActivityListadoDatosBinding
    private val adapter:AdapterCar by lazy { AdapterCar() }
    private val appDataBase: AppDataBase by lazy { AppDataBase.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoDatosBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        chargeAdapter()
        chargeData()
        handleEvents()
    }

    private fun handleEvents() {
        binding.fbNuevo.setOnClickListener{
            startActivity(Intent(this, RegistroDatos::class.java))
        }
    }

    private fun chargeData() {
        appDataBase.carDao().getCars().observe(this) {
            adapter.updateListCars(it)
        }
    }

    private fun chargeAdapter() {
        binding.rvListado.adapter = adapter
        adapter.setOnClickListenerCardEdit = { car ->
            val intent = Intent(this, RegistroDatos::class.java).apply {
                putExtra(Constants.KEY_CAR, car)
            }
            startActivity(intent)
        }
        adapter.setOnClickListenerCardDelete = { car ->
            Executors.newSingleThreadExecutor().execute {
                appDataBase.carDao().delete(car)
                runOnUiThread {
                    Toast.makeText(this, "Eliminación exitosa", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}