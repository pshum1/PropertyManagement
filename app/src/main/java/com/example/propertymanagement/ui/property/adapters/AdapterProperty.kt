package com.example.propertymanagement.ui.property.adapters//package com.example.propertymanagement.ui.property.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagement.R
import com.example.propertymanagement.data.models.Property
import com.example.propertymanagement.databinding.RowRecyclerPropertiesBinding
import com.squareup.picasso.Picasso

class AdapterProperty(var list: ArrayList<Property>): RecyclerView.Adapter<AdapterProperty.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowRecyclerPropertiesBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(l: ArrayList<Property>){
        list = l
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: RowRecyclerPropertiesBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(property: Property){
            binding.tvPropertyAddress.text = property.address
            binding.tvPropertyCity.text = property.city
            binding.tvPropertyState.text = " ,${property.state}"
            binding.tvPropertyCountry.text = property.country
            binding.tvPropertyPrice.text = "$" + property.purchasePrice
            Picasso.get().load(property.image).placeholder(R.drawable.ic_baseline_money_24).error(R.drawable.ic_baseline_notifications_24).into(binding.imgViewProperties)
        }
    }


}