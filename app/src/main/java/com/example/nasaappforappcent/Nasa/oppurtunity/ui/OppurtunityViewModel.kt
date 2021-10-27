package com.example.nasaappforappcent.Nasa.oppurtunity.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.nasaappforappcent.Nasa.network.ApiService
import com.example.nasaappforappcent.Nasa.oppurtunity.paging.OppurtunityPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OppurtunityViewModel
@Inject
constructor(
    private val apiService: ApiService
) : ViewModel() {
    private val currentCamera  = MutableLiveData<String?>(null)

    val listData = currentCamera.switchMap {
        Pager(PagingConfig(pageSize = 1)) {

            OppurtunityPagingSource(apiService,currentCamera.value)

        }.liveData.cachedIn(viewModelScope)
    }


    fun searchFilter(query: String) {
        currentCamera.value = query
    }


}