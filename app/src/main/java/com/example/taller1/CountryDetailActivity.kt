package com.example.taller1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.taller1.databinding.ActivityCountryDetailBinding

class CountryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar View Binding
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recibir los datos desde el Intent
        val name = intent.getStringExtra("name")
        val nativeName = intent.getStringExtra("nativeName")
        val alpha3Code = intent.getStringExtra("alpha3Code")
        val currency = intent.getStringExtra("currency")
        val currencySymbol = intent.getStringExtra("currencySymbol")
        val flagUrl = intent.getStringExtra("flagUrl")
        val region = intent.getStringExtra("region")
        val subRegion = intent.getStringExtra("subRegion")
        val latitude = intent.getStringExtra("latitude")
        val longitude = intent.getStringExtra("longitude")
        val area = intent.getStringExtra("area")
        val numericCode = intent.getStringExtra("numericCode")

        // Configurar los elementos del layout con los datos recibidos usando View Binding
        binding.countryName.text = name
        binding.nativeName.text = nativeName
        binding.alpha3Code.text = alpha3Code
        binding.currencyInfo.text = "$currency ($currencySymbol)"
        binding.regionInfo.text = region
        binding.subRegionInfo.text = subRegion
        binding.locationInfo.text = "Lat: $latitude, Lon: $longitude"
        binding.areaInfo.text = "Area: $area km²"

        // Cargar la bandera usando Glide
        Glide.with(this)
            .load(flagUrl)
            .into(binding.flagImage)
    }
}
