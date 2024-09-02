package com.cocodev2500.androidcomponent.repositories

import android.util.Log
import com.cocodev2500.androidcomponent.apis.RetrofitInstance
import com.cocodev2500.androidcomponent.entities.routes.RouteResponse
import retrofit2.Response

class routeRepository {

    suspend fun getRoute(apikey : String, start : String, end : String) : Response<RouteResponse> {
        Log.e("API_KEY",apikey)
        Log.e("start",start)
        Log.e("end",end)
        return RetrofitInstance.API.getRoute(
            apikey
            , start
            , end
        )
    }
}