package com.example.nasaappforappcent.Nasa.oppurtunity.adapter

import android.app.Dialog
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaappforappcent.Nasa.oppurtunity.model.Opportunity
import com.example.nasaappforappcent.databinding.RoverLayoutBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nasaappforappcent.R

class OpportunityAdapter : PagingDataAdapter<Opportunity,
        OpportunityAdapter.ImageViewHolder>(diffCallback) {


    inner class ImageViewHolder(
        val binding: RoverLayoutBinding
    ) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Opportunity>() {
            override fun areItemsTheSame(oldItem: Opportunity, newItem: Opportunity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Opportunity, newItem: Opportunity): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {

        var dialog=Dialog(parent.context)
        dialog.setContentView(R.layout.rover_pop_up)
        var vHolder=ImageViewHolder(
                RoverLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                ))
        vHolder.itemView.setOnClickListener{


            var roverPhoto=dialog.findViewById<ImageView>(R.id.rover_photo)
            var roverDate=dialog.findViewById<TextView>(R.id.rover_date)
            var roverName=dialog.findViewById<TextView>(R.id.rover_name)
            var roverCamera=dialog.findViewById<TextView>(R.id.rover_camera)
            var roverStatus=dialog.findViewById<TextView>(R.id.rover_status)
            var roverLaunchDate=dialog.findViewById<TextView>(R.id.rover_launch_date)
            var roverLandingDate=dialog.findViewById<TextView>(R.id.rover_landing_date)

            roverCamera.setText("Rover Camera:"+getItem(vHolder.bindingAdapterPosition)?.camera?.name.toString())
            roverName.setText("Rover Name:"+getItem(vHolder.bindingAdapterPosition)?.rover?.name.toString())
            roverDate.setText("Rover Date:"+getItem(vHolder.bindingAdapterPosition)?.earth_date.toString())
            roverStatus.setText("Rover Status:"+getItem(vHolder.bindingAdapterPosition)?.rover?.status.toString())
            roverLaunchDate.setText("Rover Launch Date:"+getItem(vHolder.bindingAdapterPosition)?.rover?.launch_date)
            roverLandingDate.setText("Rover Landing date:"+getItem(vHolder.bindingAdapterPosition)?.rover?.landing_date.toString())

            Glide.with(roverPhoto.context).load(getItem(vHolder.bindingAdapterPosition)?.img_src)
                .apply( RequestOptions().placeholder(R.drawable.nophoto).error(R.drawable.nophoto))
                .into(roverPhoto)



            dialog.show()


        }
        return vHolder
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currChar = getItem(position)

        holder.binding.apply {

            holder.itemView.apply {

                tvDescription.text = "${currChar?.id}"


                Glide.with(imageView.context).load(currChar?.img_src)
                        .apply( RequestOptions().placeholder(R.drawable.nophoto).error(R.drawable.nophoto))
                        .into(imageView)

            }
        }

    }


}