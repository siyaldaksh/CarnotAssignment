package com.example.carnotassignment.networkModule

import com.google.gson.annotations.SerializedName

data class CurrentDailyPriceData(
    @SerializedName("created") var created: Int? = null,
    @SerializedName("updated") var updated: Int? = null,
    @SerializedName("created_date") var createdDate: String? = null,
    @SerializedName("updated_date") var updatedDate: String? = null,
    @SerializedName("active") var active: String? = null,
    @SerializedName("index_name") var indexName: String? = null,
    @SerializedName("org") var org: ArrayList<String> = arrayListOf(),
    @SerializedName("org_type") var orgType: String? = null,
    @SerializedName("source") var source: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("external_ws_url") var externalWsUrl: String? = null,
    @SerializedName("visualizable") var visualizable: String? = null,
    @SerializedName("field") var field: ArrayList<Field> = arrayListOf(),
    @SerializedName("external_ws") var externalWs: Int? = null,
    @SerializedName("catalog_uuid") var catalogUuid: String? = null,
    @SerializedName("sector") var sector: ArrayList<String> = arrayListOf(),
    @SerializedName("target_bucket") var targetBucket: TargetBucket? = TargetBucket(),
    @SerializedName("desc") var desc: String? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("version") var version: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("total") var total: Int? = null,
    @SerializedName("count") var count: Int? = null,
    @SerializedName("limit") var limit: String? = null,
    @SerializedName("offset") var offset: String? = null,
    @SerializedName("records") var records: ArrayList<Records> = arrayListOf()

)

class Field (
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("type")
    var type: String? = null

)

data class TargetBucket (
    @SerializedName("field")
    var field: String? = null,
    @SerializedName("index")
    var index: String? = null,
    @SerializedName("type")
    var type: String? = null
)

data class Records (
    @SerializedName("state")
    var state: String? = null,
    @SerializedName("district")
    var district: String? = null,
    @SerializedName("market")
    var market: String? = null,
    @SerializedName("commodity")
    var commodity: String? = null,
    @SerializedName("variety")
    var variety: String? = null,
    @SerializedName("arrival_date")
    var arrivalDate: String? = null,
    @SerializedName("min_price")
    var minPrice: String? = null,
    @SerializedName("max_price")
    var maxPrice: String? = null,
    @SerializedName("modal_price")
    var modalPrice: String? = null
)
