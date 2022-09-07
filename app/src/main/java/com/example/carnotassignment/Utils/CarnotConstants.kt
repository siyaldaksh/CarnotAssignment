package com.example.carnotassignment.Utils

class CarnotConstants {

    companion object{

        const val BASE_URL = "https://api.data.gov.in/"
        const val API_KEY = "579b464db66ec23bdd000001cbd47f365e5741a84a9a9f6b077a06c3"
        const val END_POINT_URL = "resource/9ef84268-d588-465a-a308-a864a43d0070"
        const val API_KEY_KEY = "api-key"
        const val FORMAT_KEY = "format"
        const val OFFSET_KEY = "offset"
        const val FORMAT_TYPE_VALUE = "json"
        const val FILTER_BY_STATE_KEY = "filters[state]"
        const val FILTER_BY_DISTRICT_KEY = "filters[district]"
        const val SORT_BY_MIN_PRICE_KEY = "sort[min_price]"
        const val SORT_BY_ARRIVAL_DATE_KEY = "sort[arrival_date]"

        const val SEARCH_STATE_DATA = "searchStateData"
        const val SEARCH_DISTRICT_DATA = "searchDistrictData"
        const val SEARCH_SORT_BY_MIN_PRICE_DATA = "searchAscData"
        const val SEARCH_SORT_BY_ARRIVAL_DATE_SORT = "searchDescData"

        var offset = 0
    }
}