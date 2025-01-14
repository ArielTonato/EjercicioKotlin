package com.example.appgestiondatos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appgestiondatos.databinding.ActivityDetalleImagenCharacterBinding
import com.example.appgestiondatos.model.Character
import com.squareup.picasso.Picasso

class DetalleImagenCharacter: AppCompatActivity() {
    private lateinit var binding: ActivityDetalleImagenCharacterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleImagenCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val character = intent.getSerializableExtra("character") as Character
        setupCharacterData(character)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupCharacterData(character: Character) {
        with(binding) {
            txtName.text = character.name
            txtStatus.text = "Status: ${character.status}"
            txtSpecies.text = "Species: ${character.species}"
            txtGender.text = "Gender: ${character.gender}"
            txtOrigin.text = "Origin: ${character.origin.name}"
            txtLocation.text = "Location: ${character.location.name}"
            Picasso.get().load(character.image)
                .error(R.drawable.ic_launcher_background)
                .into(imgCharacter)
        }
    }
}