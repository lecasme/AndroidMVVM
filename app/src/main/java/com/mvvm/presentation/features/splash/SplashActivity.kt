package com.mvvm.presentation.features.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.mvvm.R
import com.mvvm.presentation.features.home.HomeActivity
import org.koin.android.viewmodel.ext.android.viewModel


class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val parentLayout: View = findViewById(android.R.id.content)

        viewModel.status.observe(this, {
            if (it) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Snackbar.make(parentLayout, "Ocurrio un error inesperado", Snackbar.LENGTH_LONG).show()
            }
        })
    }
}