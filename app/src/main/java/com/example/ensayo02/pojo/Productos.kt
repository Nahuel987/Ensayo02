package com.example.ensayo02.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos_table")
data class Productos (@PrimaryKey val id:Int, val name:String, val price:String, val image:String){
}