package com.example.nasaappforappcent.Nasa.oppurtunity.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nasaappforappcent.Nasa.network.ApiService
import com.example.nasaappforappcent.Nasa.oppurtunity.model.Opportunity
import android.util.Log
import androidx.lifecycle.MutableLiveData

class OppurtunityPagingSource(
    private val apiService: ApiService,
    private val camera: String?
) : PagingSource<Int, Opportunity>() {

override fun getRefreshKey(state: PagingState<Int, Opportunity>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, Opportunity> {

        return try {

            val currentPage = params.key ?: 1
            val response = apiService.getOpportunityPhotos(page =currentPage, camera = camera)
            val responseData = mutableListOf<Opportunity>()

            val data = response.body()?.photos ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {

            LoadResult.Error(e)

        }

    }
}