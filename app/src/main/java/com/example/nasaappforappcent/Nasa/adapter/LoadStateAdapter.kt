package com.example.nasaappforappcent.Nasa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaappforappcent.R

class RoverLoadStateAdapter (
    private val retry: () -> Unit
) : LoadStateAdapter<RoverLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {

        val progress = holder.itemView.findViewById<ProgressBar>(R.id.load_state_progress)
        progress.isVisible = loadState is LoadState.Loading
        if (loadState is LoadState.Error){
           progress.isVisible=true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.progress_bar, parent, false)
        )
    }

     class LoadStateViewHolder(private val view: View) : RecyclerView.ViewHolder(view)
}