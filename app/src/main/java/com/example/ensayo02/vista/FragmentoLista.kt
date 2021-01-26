package com.example.ensayo02.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ensayo02.R
import com.example.ensayo02.pojo.Productos
import com.example.ensayo02.viewModel.ProductosViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentoLista.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentoLista : Fragment(), ProductosAdaptador.PasarDatos {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var listaProductos = ArrayList<Productos>()
    lateinit var viewAdapter: ProductosAdaptador
    lateinit var mViewModel: ProductosViewModel

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
        val view:View=inflater.inflate(R.layout.fragment_fragmento_lista, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)

        //iniciando el view model
        mViewModel = ViewModelProvider(activity!!).get(ProductosViewModel::class.java)

        //recibe respuesta de retrofit y se ingresan datos a la database
        mViewModel.fetchFromServer()

        //mViewModel.getDataFromDB()

        //iniciando el adaptador, se le pone onclick this, por la interfaz creada en productos ADAPTADOR, PARA PASAR DATOS A UN NUVO FRAGMENTO
        viewAdapter = ProductosAdaptador(listaProductos,this)

        //pasando adaptador al recycler
        recyclerView.adapter = viewAdapter

        mViewModel.getDataFromDB().observe(this, Observer {
            viewAdapter.updateData(it)
        })

        return view

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentoLista.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentoLista().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    //metodo de productos adaptador, el cual recibe los datos del holder
    override fun pasarDatos(producto: Productos){

        //recibo datos desde el adaptador
        val tempId:String=producto.id.toString()
        val tempNombre:String=producto.name.toString()
        val tempPrecio:String=producto.price.toString()
        val tempImagen:String=producto.image.toString()

        //creo un objeto bundle con el cual enviare los datos RECIBIDOS del adaptador al FRAGMENTO DETALLE
        val mybundle=Bundle()

        //inserto los datos que quiero enviar
        mybundle.putString("EL ID", tempId)
        mybundle.putString("EL NOMBRE", tempNombre)
        mybundle.putString("EL PRECIO", tempPrecio)
        mybundle.putString("EL IMAGEN", tempImagen)


        //instancio fragmento detalle
        val fragmentoDetalle= FragmentoDetalle()

        //le ingreso los datos a enviar al objeto bundle
        fragmentoDetalle.arguments=mybundle



        // FragmentoDetalle= newInstance(tempId,tempNombre,tempPrecio,tempImagen)

        //activity!!.supportFragmentManager.beginTransaction().replace(R.id.contenedorFragmentos,FragmentoDetalle.newInstance("","")).addToBackStack(null).commit()

        //me voy al framento detalles
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.contenedorFragmentos,fragmentoDetalle).addToBackStack(null).commit()
    }
}