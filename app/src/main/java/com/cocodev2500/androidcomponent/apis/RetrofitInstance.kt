package com.cocodev2500.androidcomponent.apis
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {


    //LAZY se utiliza para que la variable se inicialice solo cuando se necesite
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl("https://api.openrouteservice.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val API: api by lazy {
        retrofit.create(api::class.java)
    }

}