package com.example.nasaappforappcent.Nasa.spirit.ui

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.nasaappforappcent.Nasa.curiosity.paging.CuriosityPagingSource
import com.example.nasaappforappcent.Nasa.network.ApiService
import com.example.nasaappforappcent.Nasa.spirit.paging.SpiritPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SpiritViewModel
@Inject
constructor(
    private val apiService: ApiService
) : ViewModel() {
    private val currentCamera  = MutableLiveData<String?>(null)

    val listData = currentCamera.switchMap {
        Pager(PagingConfig(pageSize = 1)) {

            SpiritPagingSource(apiService,currentCamera.value)

        }.liveData.cachedIn(viewModelScope)
    }


    fun searchFilter(query: String) {
        currentCamera.value = query
    }


}