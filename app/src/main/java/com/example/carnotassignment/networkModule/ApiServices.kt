package com.example.carnotassignment.networkModule


import com.example.carnotassignment.Utils.CarnotConstants.Companion.API_KEY_KEY
import com.example.carnotassignment.Utils.CarnotConstants.Companion.END_POINT_URL
import com.example.carnotassignment.Utils.CarnotConstants.Companion.FILTER_BY_DISTRICT_KEY
import com.example.carnotassignment.Utils.CarnotConstants.Companion.FILTER_BY_STATE_KEY
import com.example.carnotassignment.Utils.CarnotConstants.Companion.FORMAT_KEY
import com.example.carnotassignment.Utils.CarnotConstants.Companion.OFFSET_KEY
import com.example.carnotassignment.Utils.CarnotConstants.Companion.SORT_BY_ARRIVAL_DATE_KEY
import com.example.carnotassignment.Utils.CarnotConstants.Companion.SORT_BY_MIN_PRICE_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET(END_POINT_URL)
    suspend fun getCurrentDailyPrice(@Query(API_KEY_KEY) apiKey: String, @Query(FORMAT_KEY) responseDataType : String, @Query(
        OFFSET_KEY) offset :Int) : CurrentDailyPriceData

    @GET(END_POINT_URL)
    suspend fun getPriceByState(@Query(API_KEY_KEY) apiKey: String, @Query(FORMAT_KEY) responseDataType : String, @Query(
        OFFSET_KEY) offset :Int,@Query(FILTER_BY_STATE_KEY) filter:String) : CurrentDailyPriceData

    @GET(END_POINT_URL)
    suspend fun getPriceByDistrict(@Query(API_KEY_KEY) apiKey: String, @Query(FORMAT_KEY) responseDataType : String, @Query(
        OFFSET_KEY) offset :Int,@Query(FILTER_BY_DISTRICT_KEY) filter:String) : CurrentDailyPriceData

    @GET(END_POINT_URL)
    suspend fun getPriceSortedByPrice(@Query(API_KEY_KEY) apiKey: String, @Query(FORMAT_KEY) responseDataType : String, @Query(
        OFFSET_KEY) offset :Int,@Query(SORT_BY_MIN_PRICE_KEY) filter:String) : CurrentDailyPriceData

    @GET(END_POINT_URL)
    suspend fun getPriceSortedByDate(@Query(API_KEY_KEY) apiKey: String, @Query(FORMAT_KEY) responseDataType : String, @Query(
        OFFSET_KEY) offset :Int,@Query(SORT_BY_ARRIVAL_DATE_KEY) filter:String) : CurrentDailyPriceData


}