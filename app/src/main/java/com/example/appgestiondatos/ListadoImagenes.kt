package com.example.appgestiondatos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appgestiondatos.databinding.ActivityListadoImagenesBinding
import com.example.appgestiondatos.model.Pokemon

class ListadoImagenes : AppCompatActivity() {
    private lateinit var binding: ActivityListadoImagenesBinding
    private val adapterPokemon: AdapterPokemon by lazy { AdapterPokemon() }
    private var listPokemon: List<Pokemon> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoImagenesBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        configureAdapter()
        loadData()
    }

    private fun loadData() {
        listPokemon = listOf(
            Pokemon("Bulbasaur","https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png"),
            Pokemon("Ivysaur","https://assets.pokemon.com/assets/cms2/img/pokedex/full/002.png"),
            Pokemon("Venusaur","https://assets.pokemon.com/assets/cms2/img/pokedex/full/003.png"),
            Pokemon("Charmander","https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png"),
            Pokemon("Charmeleon","https://assets.pokemon.com/assets/cms2/img/pokedex/full/005.png"),
            Pokemon("Charizard","https://assets.pokemon.com/assets/cms2/img/pokedex/full/006.png"),
            Pokemon("Squirtle","https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png"),
            Pokemon("Wartortle","https://assets.pokemon.com/assets/cms2/img/pokedex/full/008.png"),
            Pokemon("Blastoise","https://assets.pokemon.com/assets/cms2/img/pokedex/full/009.png"),
            Pokemon("Caterpie","https://assets.pokemon.com/assets/cms2/img/pokedex/full/010.png"),
        )
        adapterPokemon.updateListPokemos(listPokemon)
    }

    private fun configureAdapter() {
        binding.rvImagenes.adapter = adapterPokemon
    }
}