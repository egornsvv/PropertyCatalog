package com.example.realestatecatalog.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.realestatecatalog.R
import com.example.realestatecatalog.data.RealEstateProperty
import com.example.realestatecatalog.databinding.ItemPropertyBinding
import com.squareup.picasso.Picasso

class PropertyAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {

    private var properties: List<RealEstateProperty> = emptyList()

    inner class PropertyViewHolder(itemView: ItemPropertyBinding) : RecyclerView.ViewHolder(itemView.root) {
        val priceTextView = itemView.priceTextView
        val textViewArea = itemView.squareMetersTextView
        val textViewAddress = itemView.textViewAddress
        val imageVie = itemView.viewPager

        init {
            itemView.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(properties[position])
                }
            }
        }
        fun binding(property: RealEstateProperty) {
            if (!property.photo.isNullOrEmpty()) {
                Picasso.get().load(property.photo).into(imageVie)
            } else {
                imageVie.setImageResource(R.drawable.ic_launcher_background)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(property: RealEstateProperty)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val binding = ItemPropertyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PropertyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val currentProperty = properties[position]
        holder.priceTextView.text = currentProperty.price.toString()
        holder.textViewArea.text = currentProperty.area.toString()
        holder.textViewAddress.text = currentProperty.address

        holder.binding(currentProperty)
        holder.itemView.setOnClickListener {
            listener.onItemClick(currentProperty)
        }
    }

    override fun getItemCount(): Int {
        return properties.size
    }

    fun setProperties(properties: List<RealEstateProperty>) {
        this.properties = properties
        notifyDataSetChanged()
    }

}
