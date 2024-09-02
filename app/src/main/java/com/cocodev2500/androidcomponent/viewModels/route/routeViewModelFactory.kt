package com.cocodev2500.androidcomponent.viewModels.route

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cocodev2500.androidcomponent.repositories.routeRepository

// ViewModelFactory for routeViewModel : este factory se encarga de crear instancias de routeViewModel
class routeViewModelFactory(private val repository: routeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(routeViewModel::class.java)) {
            return routeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}