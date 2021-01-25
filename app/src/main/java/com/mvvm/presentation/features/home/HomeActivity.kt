package com.mvvm.presentation.features.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.R
import com.mvvm.presentation.features.home.adapter.PokemonAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel.pokemon.observe(this, Observer {
            findViewById<RecyclerView>(R.id.rcvPokemons).apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = PokemonAdapter(it)
                addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.HORIZONTAL).apply { })
                addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            }
        })
    }
}