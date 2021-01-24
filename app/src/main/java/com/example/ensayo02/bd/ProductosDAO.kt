package com.example.ensayo02.bd

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ensayo02.pojo.Productos

@Dao
interface ProductosDAO {

    //Insertar un listado de productos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProductos(listProductos: List<Productos>)

    // Insertar 1 producto
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductos(productos: Productos)

    // traer todos los elementos de la tabla
    @Query("SELECT * FROM productos_table ORDER BY id ASC")
    fun getAllListProductos() : LiveData<List<Productos>>

    //Borrarlos todos
    @Query("DELETE FROM productos_table")
    suspend fun deleteAllPProductos()
}