package com.example.taller1

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class CountryAdapter(private val countryList: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.country_item, parent, false)
        return CountryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val currentCountry = countryList[position]

        holder.countryName.text = currentCountry.name
        holder.nativeName.text = currentCountry.nativeName
        holder.alpha3Code.text = currentCountry.alpha3Code
        holder.currency.text = "${currentCountry.currency} (${currentCountry.symbol})"

        // Cargar la imagen de la bandera usando Glide
        Glide.with(holder.itemView.context)
            .load(currentCountry.flagUrl)
            .into(holder.flagImage)

        // Configurar la acción del botón de llamada
        holder.callButton.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${currentCountry.numericCode}")
            }
            context.startActivity(intent)
        }

        // Clic en la tarjeta completa para abrir los detalles del país
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, CountryDetailActivity::class.java).apply {
                putExtra("name", currentCountry.name)
                putExtra("nativeName", currentCountry.nativeName)
                putExtra("alpha3Code", currentCountry.alpha3Code)
                putExtra("currency", currentCountry.currency)
                putExtra("currencySymbol", currentCountry.symbol)
                putExtra("flagUrl", currentCountry.flagUrl)
                putExtra("region", currentCountry.region)
                putExtra("subRegion", currentCountry.subRegion)
                putExtra("latitude", currentCountry.latitude)
                putExtra("longitude", currentCountry.longitude)
                putExtra("area", currentCountry.area)
                putExtra("numericCode", currentCountry.numericCode)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = countryList.size

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryName: TextView = itemView.findViewById(R.id.text_view_country_name)
        val nativeName: TextView = itemView.findViewById(R.id.text_view_native_name)
        val alpha3Code: TextView = itemView.findViewById(R.id.text_view_alpha3code)
        val currency: TextView = itemView.findViewById(R.id.text_view_currency)
        val flagImage: ImageView = itemView.findViewById(R.id.image_view_flag)
        val callButton: ImageButton = itemView.findViewById(R.id.button_call)
    }
}
