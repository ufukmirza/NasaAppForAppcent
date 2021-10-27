package com.example.nasaappforappcent.Nasa.curiosity.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nasaappforappcent.Nasa.curiosity.model.Curiosity
import com.example.nasaappforappcent.Nasa.network.ApiService
import com.example.nasaappforappcent.Nasa.oppurtunity.model.Opportunity


class CuriosityPagingSource(
    private val apiService: ApiService,
    private val camera: String?
) : PagingSource<Int, Curiosity>() {

    override fun getRefreshKey(state: PagingState<Int, Curiosity>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, Curiosity> {

        return try {

            val currentPage = params.key ?: 1
            val response = apiService.getCuriosityPhotos(page =currentPage, camera = camera)
            val responseData = mutableListOf<Curiosity>()

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