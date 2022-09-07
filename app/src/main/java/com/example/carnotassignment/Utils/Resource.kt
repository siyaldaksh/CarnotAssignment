package com.example.carnotassignment.Utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?,val load: Boolean) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null,false)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg,false)
        }

        fun <T> loading(loadingStatus: Boolean): Resource<T> {
            return Resource(Status.LOADING, null, null,loadingStatus)
        }


    }

}