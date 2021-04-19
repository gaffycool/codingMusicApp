package com.hh.codingexercise.data.api

import com.google.gson.annotations.SerializedName

data class AlbumData(

    @SerializedName("id")
    val id: Int,

    /**
     * userId of album
     */
    @SerializedName("userId")
    val userId: Int? = null,

    /**
     * Title of the album
     */
    @SerializedName("title")
    val title: String? = null,


) {
}