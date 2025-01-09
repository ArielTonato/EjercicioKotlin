package com.example.appgestiondatos

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appgestiondatos.databinding.ItemCarBinding
import com.example.appgestiondatos.databinding.ItemPokemonBinding
import com.example.appgestiondatos.model.Car
import com.example.appgestiondatos.model.Pokemon
import com.squareup.picasso.Picasso

class AdapterPokemon(var pokemons: List<Pokemon> = emptyList()):RecyclerView.Adapter<AdapterPokemon.AdapterCardViewHolder>(){

    //Funciones para editar y eliminar
    lateinit var setOnClickPokemon: (Pokemon) -> Unit

    inner class AdapterCardViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private var binding: ItemPokemonBinding = ItemPokemonBinding.bind(itemView)

        fun bind(pokemon: Pokemon){
            with(binding){
                txtPokemon.text = pokemon.nombre
                Picasso.get().load(pokemon.url).error(R.drawable.ic_launcher_background).into(imgPokemon)
                root.setOnClickListener{
                    setOnClickPokemon(pokemon)
                    }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return AdapterCardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: AdapterCardViewHolder, position: Int) {
        val car = pokemons[position]
        holder.bind(car)
    }

    fun updateListPokemos(pokemons: List<Pokemon>){
        this.pokemons = pokemons
        notifyDataSetChanged()
    }
}