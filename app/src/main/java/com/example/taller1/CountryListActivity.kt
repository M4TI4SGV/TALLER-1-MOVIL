package com.example.taller1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.io.InputStream

class CountryListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_list)

        // Recibir la región seleccionada desde el Intent
        val selectedRegion = intent.getStringExtra("region")

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_countries)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val countries = loadCountriesFromAssets(selectedRegion)

        val adapter = CountryAdapter(countries)
        recyclerView.adapter = adapter
    }

    private fun loadCountriesFromAssets(region: String?): List<Country> {
        val countries = mutableListOf<Country>()
        val json: String?

        try {

            val inputStream: InputStream = assets.open("paises.json")
            json = inputStream.bufferedReader().use { it.readText() }
            val jsonObject = JSONObject(json)
            val jsonArray = jsonObject.getJSONArray("Countries")

            // Iterar sobre el JSONArray para crear objetos Country
            for (i in 0 until jsonArray.length()) {
                val countryObject = jsonArray.getJSONObject(i)
                val countryRegion = countryObject.getString("Region")

                // Filtrar por la región seleccionada
                if (countryRegion == region) {
                    val country = Country(
                        name = countryObject.getString("Name"),
                        nativeName = countryObject.getString("NativeName"),
                        alpha3Code = countryObject.getString("Alpha3Code"),
                        currency = countryObject.getString("CurrencyName"),
                        symbol = countryObject.getString("CurrencySymbol"),
                        flagUrl = countryObject.getString("FlagPng"),
                        region = countryObject.getString("Region"),
                        subRegion = countryObject.getString("SubRegion"),
                        latitude = countryObject.getString("Latitude"),
                        longitude = countryObject.getString("Longitude"),
                        area = countryObject.getString("Area"),
                        numericCode = countryObject.getString("NumericCode")
                    )
                    countries.add(country)
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return countries
    }
}

