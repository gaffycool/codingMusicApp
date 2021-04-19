package com.hh.codingexercise.data.api

/**
 * Describes the response from album service API.
 */
data class AlbumResponse(
    var albums: List<AlbumData> = emptyList()
)