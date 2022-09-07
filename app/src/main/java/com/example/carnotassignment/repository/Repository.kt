package com.example.carnotassignment.repository

import com.example.carnotassignment.networkModule.ApiServices
import com.example.carnotassignment.networkModule.CurrentDailyPriceData
import javax.inject.Inject

class Repository @Inject constructor(private val apiServices: ApiServices){

    suspend fun getCurrentDailyPrice(apiKey:String,format:String,offset:Int) : CurrentDailyPriceData {
       return apiServices.getCurrentDailyPrice(apiKey,format,offset)
    }

    suspend fun getPriceByState(apiKey:String,format:String,offset:Int,filter:String) : CurrentDailyPriceData {
        return apiServices.getPriceByState(apiKey,format,offset,filter)
    }

    suspend fun getPriceByDistrict(apiKey:String,format:String,offset:Int,filter:String) : CurrentDailyPriceData {
        return apiServices.getPriceByDistrict(apiKey,format,offset,filter)
    }

    suspend fun getPriceSortedByPrice(apiKey:String,format:String,offset:Int,filter:String) : CurrentDailyPriceData {
        return apiServices.getPriceSortedByPrice(apiKey,format,offset,filter)
    }

    suspend fun getPriceSortedByDate(apiKey:String,format:String,offset:Int,filter:String) : CurrentDailyPriceData {
        return apiServices.getPriceSortedByDate(apiKey,format,offset,filter)
    }


}