package com.example.marvelappapi.data

import com.example.marvelappapi.WEB_SERVICE_URL
import retrofit2.Response
import retrofit2.http.GET

interface MarvelService {
    @GET(WEB_SERVICE_URL)
    suspend fun getMarvelData(): MarvelResponse
}

//interface MarvelService {
//    @GET("/marvel-heroes")
//    suspend fun getMarvelData(): Response<List<Marvel>>
//}
