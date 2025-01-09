package com.example.appgestiondatos

import android.content.Intent
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
            Pokemon("Bulbasaur", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png", "Un Pokémon Planta/Veneno conocido por el bulbo en su espalda, que almacena energía solar."),
            Pokemon("Ivysaur", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/002.png", "La evolución de Bulbasaur, con un bulbo que está a punto de florecer."),
            Pokemon("Venusaur", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/003.png", "Un Pokémon robusto con una flor gigante en su espalda que libera un aroma relajante."),
            Pokemon("Charmander", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png", "Un Pokémon de tipo Fuego cuya cola arde intensamente según su estado emocional."),
            Pokemon("Charmeleon", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/005.png", "La evolución de Charmander, conocido por su temperamento agresivo y su cola aún más ardiente."),
            Pokemon("Charizard", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/006.png", "Un imponente Pokémon de tipo Fuego/Volador que escupe llamas capaces de fundir rocas."),
            Pokemon("Squirtle", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png", "Un Pokémon de tipo Agua que usa su caparazón para protegerse y lanza agua con gran precisión."),
            Pokemon("Wartortle", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/008.png", "La evolución de Squirtle, con orejas emplumadas y una cola peluda que simbolizan longevidad."),
            Pokemon("Blastoise", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/009.png", "Un Pokémon de tipo Agua con cañones en su caparazón capaces de disparar agua a alta presión."),
            Pokemon("Caterpie", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/010.png", "Un Pokémon tipo Bicho que segrega un hilo para defenderse y evolucionar rápidamente."),
            )
        adapterPokemon.updateListPokemos(listPokemon)
    }

    private fun configureAdapter() {
        binding.rvImagenes.adapter = adapterPokemon

        adapterPokemon.setOnClickPokemon = { pokemon ->
            val intent = Intent(this, DetalleImagen::class.java)
            intent.putExtra("pokemon", pokemon)
            startActivity(intent)
        }
    }
}