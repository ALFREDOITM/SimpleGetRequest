package com.example.simplegetrequest

import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplegetrequest.databinding.ItemNameBinding
import org.json.JSONArray
import org.json.JSONObject

class MainAdapter(private val pokemons: JSONArray): RecyclerView.Adapter<MainAdapter.PokemonHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.PokemonHolder {
        val binding = ItemNameBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return PokemonHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.render(pokemons.getJSONObject(position))
    }

    override fun getItemCount(): Int = pokemons.length()

    class PokemonHolder(val binding: ItemNameBinding): RecyclerView.ViewHolder(binding.root){
        fun render(pokemon: JSONObject){
            binding.pokeName.setText(pokemon.getString("name"))
        }
    }
}