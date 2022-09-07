package com.example.carnotassignment.ui.currentDailyPrice


import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.carnotassignment.Utils.CarnotConstants
import com.example.carnotassignment.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrentDailyPriceListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    val searchClicked = MutableLiveData<Boolean>()
    val searchClickedLiveData: LiveData<Boolean>
        get() = searchClicked

    var searchText  = MutableLiveData<String>("")
    var ascDesc = MutableLiveData<String>("")

    fun searchData(){
        searchClicked.value = true
    }

    val currentDailyPrice = Pager(PagingConfig(pageSize = 10)) {
        DataSource(repository,"null","null")
    }.flow

    val currentDailyPriceByState = Pager(PagingConfig(pageSize = 10)) {
        DataSource(repository,CarnotConstants.SEARCH_STATE_DATA, searchText.value!!)
    }.flow

    val currentDailyPriceByDistrict = Pager(PagingConfig(pageSize = 10)) {
        DataSource(repository,CarnotConstants.SEARCH_DISTRICT_DATA,searchText.value!!)
    }.flow

    val currentDailyPriceSortedByMinPrice = Pager(PagingConfig(pageSize = 10)) {
        DataSource(repository,CarnotConstants.SEARCH_SORT_BY_MIN_PRICE_DATA,ascDesc.value!!)
    }.flow

    val currentDailyPriceSortedByDate = Pager(PagingConfig(pageSize = 10)) {
        DataSource(repository,CarnotConstants.SEARCH_SORT_BY_ARRIVAL_DATE_SORT,ascDesc.value!!)
    }.flow

}