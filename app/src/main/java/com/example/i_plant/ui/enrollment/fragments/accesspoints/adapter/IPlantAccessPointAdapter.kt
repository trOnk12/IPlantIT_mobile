package com.example.i_plant.ui.enrollment.fragments.accesspoints.adapter

import android
.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.i_plant.data.model.AccessPointDevice
import com.example.i_plant.databinding.ItemViewIPlantAccesPointBinding

class IPlantAccessPointAdapter : RecyclerView.Adapter<IPlantAccessPointAdapter.ViewHolder>() {

    private val accessPointDevices: ArrayList<AccessPointDevice> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewIPlantAccesPointBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount() = accessPointDevices.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(accessPointDevices[position])

    class ViewHolder(private val binding: ItemViewIPlantAccesPointBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(accessPointDevice: AccessPointDevice) {
            binding.accessPointDevice = accessPointDevice
            binding.executePendingBindings()
        }

    }

}