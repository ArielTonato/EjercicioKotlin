package com.example.appgestiondatos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.appgestiondatos.databinding.ActivityListadoImagenesCharacterBinding
import com.example.appgestiondatos.model.Character
import com.example.appgestiondatos.model.Location
import com.example.appgestiondatos.model.Origin

class ListadoImagenesCharacterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListadoImagenesCharacterBinding
    private val adapter: CharacterAdapter by lazy { CharacterAdapter() }
    private val queue by lazy { Volley.newRequestQueue(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoImagenesCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchCharacters()
    }

    private fun setupRecyclerView() {
        // Configurar el RecyclerView con el adaptador
        binding.rvImagenes.adapter = adapter
        adapter.setOnClickCharacter = { character ->
            val intent = Intent(this, DetalleImagenCharacter::class.java)
            intent.putExtra("character", character)
            startActivity(intent)
        }
    }

    private fun fetchCharacters() {
        val url = "https://rickandmortyapi.com/api/character"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val jsonArray = response.getJSONArray("results")
                val characters = mutableListOf<Character>()

                for (i in 0 until jsonArray.length()) {
                    val characterJson = jsonArray.getJSONObject(i)
                    val originJson = characterJson.getJSONObject("origin")
                    val locationJson = characterJson.getJSONObject("location")

                    characters.add(
                        Character(
                            id = characterJson.getInt("id"),
                            name = characterJson.getString("name"),
                            image = characterJson.getString("image"),
                            status = characterJson.getString("status"),
                            species = characterJson.getString("species"),
                            gender = characterJson.getString("gender"),
                            origin = Origin(originJson.getString("name")),
                            location = Location(locationJson.getString("name"))
                        )
                    )
                }
                adapter.updateList(characters)
            },
            { error ->
                Toast.makeText(this, "Error al cargar los personajes: ${error.message}", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }
}
