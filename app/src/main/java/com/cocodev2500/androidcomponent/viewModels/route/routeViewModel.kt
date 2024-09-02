package com.cocodev2500.androidcomponent.viewModels.route

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cocodev2500.androidcomponent.entities.routes.RouteResponse
import com.cocodev2500.androidcomponent.repositories.routeRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class routeViewModel(private val repository: routeRepository): ViewModel(){

      val mRouteResponse: MutableLiveData<Response<RouteResponse>> = MutableLiveData()

        fun getRoute(spikey : String, start : String, end : String){
            viewModelScope.launch {
                val response = repository.getRoute(spikey, start, end)
                mRouteResponse.value = response
            }
        }
}