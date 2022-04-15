package com.arbaelbarca.appintermediate.data.datasource.local.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class ResponseListSheet : ArrayList<ResponseListSheet.ResponseListSheetItem>(){
    @Parcelize
    data class ResponseListSheetItem(
        @SerializedName("content")
        val content: String?,
        @SerializedName("email")
        val email: String?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("title")
        val title: String?
    ) : Parcelable
}