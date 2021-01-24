package com.example.ensayo02.remoto

import com.example.ensayo02.pojo.Productos
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("products")
    fun getAllPersonajes(): Call<List<Productos>>

}
