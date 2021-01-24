package com.example.ensayo02.vista

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ensayo02.R
import com.example.ensayo02.pojo.Productos


class ProductosAdaptador(var list: List<Productos>, val onClick:PasarDatos) :
    RecyclerView.Adapter<ProductosAdaptador.ProductosHolder>() {


    fun updateData(listProductos: List<Productos>) {
        Log.d("ACTUALIZA", "update ${listProductos.size}")
        list = listProductos

        //metodo del recyclerView
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.contenedor_items, parent, false)
        return ProductosHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductosHolder, position: Int) {

        val producto = list[position]
        holder.id.text = producto.id.toString()
        holder.name.text = producto.name
        holder.price.text = producto.price

        //mostrar con GLIDE
        val imgURL: String = producto.image

        Glide.with(holder.img.getContext())
            .load(imgURL)
            .centerCrop()
            .into(holder.img)


        //*****intento de pasar a un fragmento detalle*****//


        holder.img.setOnClickListener {


            onClick.pasarDatos(producto)

            //Toast.makeText(holder.img.getContext(),"holaaaa", Toast.LENGTH_SHORT).show()

            // fragDetail()


        }





    }// onBindViewHolder

    /*
    private fun fragDetail() {
        TODO("Not yet implemented")
       supportFragmentManager.beginTransaction().replace(R.id.contenedorFragmentos,FragmentoDetalle.newInstance("","")).addToBackStack(null).commit()
    }*/

    interface PasarDatos{

        fun pasarDatos(producto: Productos)

    }


    class ProductosHolder(view: View) : RecyclerView.ViewHolder(view) {

        val id: TextView = itemView.findViewById(R.id.txtId)
        val name: TextView = itemView.findViewById(R.id.txtName)
        val price: TextView = itemView.findViewById(R.id.txtPrice)
        val img: ImageView = itemView.findViewById(R.id.image)

    }//class ViewHolder
}