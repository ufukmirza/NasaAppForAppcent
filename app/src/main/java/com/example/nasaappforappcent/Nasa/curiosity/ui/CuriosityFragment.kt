package com.example.nasaappforappcent.Nasa.curiosity.ui

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nasaappforappcent.Nasa.adapter.RoverLoadStateAdapter
import com.example.nasaappforappcent.Nasa.curiosity.adapter.CuriosityAdapter
import com.example.nasaappforappcent.R
import com.example.nasaappforappcent.databinding.FragmentCuriosityBinding
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CuriosityFragment : Fragment(R.layout.fragment_curiosity) {

    private lateinit var binding: FragmentCuriosityBinding
    private lateinit var roverAdapter: CuriosityAdapter
    private val viewModel: CuriosityViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setHasOptionsMenu(true)
        binding = FragmentCuriosityBinding.inflate(layoutInflater)
        setupRecyclerView()
        loadData()


    }

    private fun setupRecyclerView() {

        roverAdapter = CuriosityAdapter()

        view?.findViewById<RecyclerView>(R.id.recyclerViewCuriosity)?.apply {
            setHasFixedSize(true)
            adapter = roverAdapter.withLoadStateFooter(
                footer = RoverLoadStateAdapter { roverAdapter.retry() }
            )
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
        }

    }

    private fun loadData() {


        viewModel.listData.observe(viewLifecycleOwner) {
            view?.findViewById<ProgressBar>(R.id.progressBarOpportunity)?.visibility =
                View.INVISIBLE
            roverAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        val inflater: MenuInflater = requireActivity().menuInflater
        inflater.inflate(R.menu.filter_action, menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.action_filter -> {
                var dialog = Dialog(binding.root.context)
                dialog.setContentView(R.layout.rover_filter_layout)
                var chipGroup = dialog.findViewById<ChipGroup>(R.id.chips_group)
                var button = dialog.findViewById<Button>(R.id.button)
                button.setOnClickListener {

                    when (chipGroup.checkedChipId) {
                        R.id.fhaz -> {

                            binding.recyclerViewCuriosity.scrollToPosition(0)
                            viewModel.searchFilter("fhaz")
                        }
                        R.id.rhaz -> {
                            viewModel.searchFilter("rhaz")
                        }
                        R.id.mast -> {
                            viewModel.searchFilter("mast")
                        }
                        R.id.chemcam -> {
                            viewModel.searchFilter("chemcam")
                        }
                        R.id.mahli ->  viewModel.searchFilter("mahli")
                        R.id.mardi
                        ->  viewModel.searchFilter("mardi")
                        R.id.navcam
                        ->  viewModel.searchFilter("navcam")
                        R.id.pancam
                        ->  viewModel.searchFilter("pancam")
                        R.id.minites
                        ->  viewModel.searchFilter("minites")
                    }
                    dialog.cancel()

                }
                dialog.show()
                true
            }
            else -> true
        }

    }

    fun dialogScreen() {


    }


}