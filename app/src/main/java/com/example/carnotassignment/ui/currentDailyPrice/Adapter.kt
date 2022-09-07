package com.example.carnotassignment.ui.currentDailyPrice

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import carnotassignment.databinding.ListItemBinding
import com.example.carnotassignment.networkModule.Records

class Adapter : PagingDataAdapter<Records, Adapter.CarnotViewHolder>(Comparator) {


    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarnotViewHolder {
        context = parent.context
        return CarnotViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: CarnotViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindPassenger(it) }
    }

    inner class CarnotViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindPassenger(item: Records) = with(binding) {

            val name = item.state
            val dist = item.district
            val veg = item.commodity
            val minPrice = item.minPrice
            val variety = item.variety

            stateName.text = "$name - $dist"
            commodityName.text = veg
            priceText.text = minPrice + "Rs"
            varietyName.text = variety


        }
    }

    object Comparator : DiffUtil.ItemCallback<Records>() {
        override fun areItemsTheSame(oldItem: Records, newItem: Records): Boolean {
            return oldItem.arrivalDate == newItem.arrivalDate
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Records, newItem: Records): Boolean {
            return oldItem == newItem
        }
    }

}