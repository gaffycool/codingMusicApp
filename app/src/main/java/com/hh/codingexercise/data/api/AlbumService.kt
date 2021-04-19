package com.hh.codingexercise.data.api
import retrofit2.Response
import retrofit2.http.GET


interface AlbumService {

       /* @GET("albums")
        suspend fun getAlbums(): Response<AlbumResponse>*/

        @GET("albums")
        suspend fun getAlbums(): Response<List<AlbumData>>

}
