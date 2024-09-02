package com.cocodev2500.androidcomponent.apis

import com.cocodev2500.androidcomponent.entities.routes.RouteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface api {
    @GET("/v2/directions/driving-car")
    suspend fun getRoute(
        @Query("api_key") apiKey: String,
        @Query("start", encoded = true) start: String,
        @Query("end", encoded = true) end: String
    ) : Response<RouteResponse>
}