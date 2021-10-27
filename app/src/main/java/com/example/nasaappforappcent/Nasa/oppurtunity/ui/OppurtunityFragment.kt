package com.example.nasaappforappcent.Nasa.oppurtunity.ui

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
import com.example.nasaappforappcent.Nasa.oppurtunity.adapter.OpportunityAdapter
import com.example.nasaappforappcent.Nasa.adapter.RoverLoadStateAdapter
import com.example.nasaappforappcent.R
import com.example.nasaappforappcent.databinding.FragmentOppurtunityBinding
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class oppurtunityFragment : Fragment(R.layout.fragment_oppurtunity) {

    private lateinit var binding: FragmentOppurtunityBinding
    private lateinit var opportunityAdapter: OpportunityAdapter
    private val viewModel: OppurtunityViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setHasOptionsMenu(true)
        binding = FragmentOppurtunityBinding.inflate(layoutInflater)
        setupRecyclerView()
        loadData()


    }

    private fun setupRecyclerView() {

        opportunityAdapter = OpportunityAdapter()

        view?.findViewById<RecyclerView>(R.id.recyclerView)?.apply {
            setHasFixedSize(true)
            adapter = opportunityAdapter.withLoadStateFooter(
                footer = RoverLoadStateAdapter { opportunityAdapter.retry() }
            )
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
        }

    }

    private fun loadData() {

//        lifecycleScope.launch {
//            viewModel.listData.collect {
//
//                view?.findViewById<ProgressBar>(R.id.progressBarOpportunity)?.visibility =
//                    View.INVISIBLE
//                roverAdapter.submitData(it)
//                roverAdapter.notifyDataSetChanged()
//
//            }
//
//        }

        viewModel.listData.observe(viewLifecycleOwner) {
            view?.findViewById<ProgressBar>(R.id.progressBarOpportunity)?.visibility =
                View.INVISIBLE
            opportunityAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        val inflater: MenuInflater = requireActivity().menuInflater
        inflater.inflate(R.menu.filter_action, menu)


    }


    //kullanici menude grid layouta basarsa gride linear layouta basarsa linear yapiya gecer.

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

                            binding.recyclerView.scrollToPosition(0)
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