package com.example.ensayo02.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ensayo02.pojo.Productos

@Database(entities = [Productos::class], version = 1,exportSchema = false)
abstract class ProductosRoomDatabase : RoomDatabase(){

    abstract fun productosDao() : ProductosDAO

    companion object {
        @Volatile
        private var INSTANCE: ProductosRoomDatabase? = null

        fun getDatabase(context: Context): ProductosRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductosRoomDatabase::class.java,
                    "productos_database")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}