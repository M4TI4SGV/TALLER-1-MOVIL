package com.example.taller1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener referencias a los elementos del layout
        val spinner: Spinner = findViewById(R.id.spinner_regions)
        val button: Button = findViewById(R.id.button_show_countries)

        // Configurar el adapter para el Spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.regions_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        // Configurar el listener para el botón
        button.setOnClickListener {
            val selectedRegion = spinner.selectedItem.toString()
            val intent = Intent(this, CountryListActivity::class.java)
            intent.putExtra("region", selectedRegion)
            startActivity(intent)
        }
    }
}
