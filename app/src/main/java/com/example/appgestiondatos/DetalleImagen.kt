package com.example.appgestiondatos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appgestiondatos.databinding.ActivityDetalleImagenBinding
import com.example.appgestiondatos.model.Pokemon
import com.squareup.picasso.Picasso

class DetalleImagen : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleImagenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleImagenBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed() // Esto regresará a la actividad anterior
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener el Pokémon del intent
        val pokemon = intent.getSerializableExtra("pokemon") as Pokemon

        // Configurar los datos en la vista
        setupPokemonData(pokemon)
    }

    private fun setupPokemonData(pokemon: Pokemon) {
        with(binding) {
            txtNombreDetalle.text = pokemon.nombre
            txtDescripcionDetalle.text = pokemon.descripcion
            Picasso.get().load(pokemon.url).error(R.drawable.ic_launcher_background)
                .into(imgPokemonDetalle)
        }
    }
}