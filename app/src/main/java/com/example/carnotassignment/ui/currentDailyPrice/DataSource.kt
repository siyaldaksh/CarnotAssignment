package com.example.carnotassignment.ui.currentDailyPrice

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.carnotassignment.Utils.CarnotConstants
import com.example.carnotassignment.networkModule.CurrentDailyPriceData
import com.example.carnotassignment.networkModule.Records
import com.example.carnotassignment.repository.Repository

class DataSource constructor(private val repository: Repository,
                             private val searchType : String,
                             private val filterText : String) : PagingSource<Int, Records>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Records> {
        return try {

            val nextPageNumber = params.key ?: 1
            val response : CurrentDailyPriceData

            when(searchType){

                CarnotConstants.SEARCH_STATE_DATA -> {
                    response = repository.getPriceByState(
                        CarnotConstants.API_KEY,
                        CarnotConstants.FORMAT_TYPE_VALUE,
                        CarnotConstants.offset,filterText)
                }

                CarnotConstants.SEARCH_DISTRICT_DATA -> {
                    response = repository.getPriceByDistrict(
                        CarnotConstants.API_KEY,
                        CarnotConstants.FORMAT_TYPE_VALUE,
                        CarnotConstants.offset,filterText)
                }

                CarnotConstants.SEARCH_SORT_BY_MIN_PRICE_DATA -> {
                    response = repository.getPriceSortedByPrice(
                        CarnotConstants.API_KEY,
                        CarnotConstants.FORMAT_TYPE_VALUE,
                        CarnotConstants.offset,filterText)
                }

                CarnotConstants.SEARCH_SORT_BY_ARRIVAL_DATE_SORT -> {
                    response = repository.getPriceSortedByDate(
                        CarnotConstants.API_KEY,
                        CarnotConstants.FORMAT_TYPE_VALUE,
                        CarnotConstants.offset,filterText)
                }

                else -> {
                    response = repository.getCurrentDailyPrice(
                        CarnotConstants.API_KEY,
                        CarnotConstants.FORMAT_TYPE_VALUE,
                        CarnotConstants.offset)
                }
            }


            CarnotConstants.offset += 10

            val total = response.total ?: 1

            LoadResult.Page(
                data = response.records,
                prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < total) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            Log.d("RESULT",e.toString())
            LoadResult.Error(e)

        }
    }


    override fun getRefreshKey(state: PagingState<Int, Records>): Int? {
        return null
    }
}