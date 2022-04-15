package com.arbaelbarca.appintermediate.data.network.apiservice

import com.arbaelbarca.appintermediate.data.datasource.local.response.ResponseListSheet
import com.arbaelbarca.appintermediate.data.datasource.local.response.ResponseListUsers
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface ApiService {
    @GET("sheet_data")
    fun getAllDataSheet(

    ): Call<List<ResponseListSheet.ResponseListSheetItem>>

    @GET("users")
    fun getAllUsers(

    ): Call<ResponseListUsers>

    @POST("users")
    fun addUsers(
        @QueryMap params: Map<String, String>
    ): Call<JsonObject>
}