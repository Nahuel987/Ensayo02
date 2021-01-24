package com.example.ensayo02.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.ensayo02.modelo.ProductosRepositorio
import com.example.ensayo02.pojo.Productos


class ProductosViewModel (application: Application): AndroidViewModel(application){


    private val repository =  ProductosRepositorio(application)
    private val productosList = repository.passLiveDataToViewModel()


    //obtener del servidor usando retrofit
    fun fetchFromServer() {
        repository.fetchDataFromServer()
    }

    //obtener lista de personajes de la base de datos
    fun getDataFromDB(): LiveData<List<Productos>> {
        return productosList
    }


}
