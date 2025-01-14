package com.example.appgestiondatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appgestiondatos.databinding.ItemCharacterBinding
import com.example.appgestiondatos.model.Character
import com.squareup.picasso.Picasso

class CharacterAdapter(var characters: List<Character> = emptyList()): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    lateinit var setOnClickCharacter: (Character) -> Unit

    inner class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding: ItemCharacterBinding = ItemCharacterBinding.bind(itemView)

        fun bind(character: Character) {
            with(binding) {
                txtName.text = character.name
                Picasso.get().load(character.image).error(R.drawable.ic_launcher_background).into(imgCharacter)
                root.setOnClickListener {
                    setOnClickCharacter(character)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    fun updateList(newList: List<Character>) {
        characters = newList
        notifyDataSetChanged()
    }
}