package com.mvvm.presentation.features.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvvm.R
import com.mvvm.domain.models.Pokemon

class PokemonAdapter(private val pokemons: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    lateinit var context: Context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imgPokemon)
        init {
            // Define click listener for the ViewHolder's View.
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        context = viewGroup.context
        return ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.pokemon_item,
                viewGroup,
                false
            )
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val pokemon = pokemons[position]
        Glide
            .with(context)
            .load("$API_IMG_URL$position.png")
            .centerCrop()
            .into(viewHolder.imageView)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = pokemons.size

    companion object{
        const val API_IMG_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
    }

}