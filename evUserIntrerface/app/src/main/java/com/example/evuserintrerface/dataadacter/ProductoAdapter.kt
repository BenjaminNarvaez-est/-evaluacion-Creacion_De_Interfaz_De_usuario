package com.example.evuserintrerface.dataadacter



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.evuserintrerface.R
import com.example.evuserintrerface.databinding.ItemlistaBinding
import com.example.evuserintrerface.dataclass.Producto



class ProductoAdapter(var listProd: MutableList<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ProductoHolder>() {

    inner class ProductoHolder(private val binding: ItemlistaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun cargar(producto: Producto) {

            with(binding) {
                tvCodProd.text = producto.id.toString()
                tvNombreProd.text = producto.nombre
                tvPrecioProd.text = producto.precio.toString()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoHolder {

        val binding = ItemlistaBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ProductoHolder(binding)


    }

    override fun onBindViewHolder(holder: ProductoHolder, position: Int) {


        holder.cargar(listProd[position])

    }

    override fun getItemCount(): Int = listProd.size

}






