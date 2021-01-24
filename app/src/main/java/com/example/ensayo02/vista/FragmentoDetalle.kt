package com.example.ensayo02.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ensayo02.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentoDetalle.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentoDetalle : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View=inflater.inflate(R.layout.fragment_fragmento_detalle, container, false)

        //valido que el bundle no llege vacio
        if(arguments!=null){

            //asido a val id el valor que hay en la clave "EL ID"  el !! es para asegurar que si o si no es null
            val id=arguments!!.getString("EL ID")

            //creo una variable txtId que va a contener el objeto TEXT VIEW de la vista
            val txtId: TextView = view.findViewById(R.id.txtIdFragDetalle)

            //le asigno un valor a txtId
            txtId.setText(id)


            //repito proceso con los demas argumentos

            val nombre=arguments!!.getString("EL NOMBRE")
            val txtNombre: TextView = view.findViewById(R.id.txtNameFragDetalle)
            txtNombre.setText(nombre)


            val precio=arguments!!.getString("EL PRECIO")
            val txtPrecio: TextView =view.findViewById(R.id.txtPriceFragDetalle)
            txtPrecio.setText(precio)

/*
            val foto=arguments!!.getString("EL IMAGEN")
            val imagen: ImageView=view.findViewById(R.id.imageFragDetalle)
            val txtUrl:TextView=view.findViewById(R.id.txtUrl)
           // txtUrl.setText(foto)

            Glide.with(this)
                .load(txtUrl)
                .centerCrop()
                .into(imagen)
*/
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentoDetalle.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentoDetalle().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}