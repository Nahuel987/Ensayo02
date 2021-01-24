package com.example.ensayo02.modelo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.ensayo02.bd.ProductosRoomDatabase
import com.example.ensayo02.pojo.Productos
import com.example.ensayo02.remoto.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductosRepositorio (context: Context) {

    private val tag = "ProductosRepositorio"

    //esto viene  de la Base de datos
    private val db: ProductosRoomDatabase = ProductosRoomDatabase.getDatabase(context)
    private val productosList = db.productosDao().getAllListProductos()

    //pasa la lista de personajes del LiveData al ViewModel
    fun passLiveDataToViewModel() : LiveData<List<Productos>> {
        return productosList
    }

    // esto hace la llamada a retrofit
    fun fetchDataFromServer() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllPersonajes()

        call.enqueue(object : Callback<List<Productos>> {
            override fun onResponse(call: Call<List<Productos>>, response: Response<List<Productos>>) {
                Log.d(tag, response.body().toString())
                CoroutineScope(Dispatchers.IO).launch {
                    response.body()?.let { db.productosDao().insertAllProductos(it) }
                }
            }
            override fun onFailure(call: Call<List<Productos>>, t: Throwable) {
                Log.d(tag, t.message.toString())
            }
        })

    }


}