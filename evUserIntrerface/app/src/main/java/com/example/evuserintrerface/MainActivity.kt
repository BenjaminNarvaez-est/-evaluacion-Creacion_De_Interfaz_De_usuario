package com.example.evuserintrerface

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.evuserintrerface.dataadacter.ProductoAdapter
import com.example.evuserintrerface.databinding.ActivityMainBinding
import com.example.evuserintrerface.dataclass.Producto


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var listaProd = ArrayList<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniciar()
    }


    private fun agregar() {

        try {
            with(binding) {

                val id: Int = etID.text.toString().toInt()
                val nombre: String = etNombreproducto.text.toString()
                val precio: Double = etPrecio.text.toString().toDouble()
                val prod = Producto(id, nombre, precio)

                listaProd.add(prod)

                rvProductos.layoutManager = LinearLayoutManager(this@MainActivity)
                rvProductos.adapter = ProductoAdapter(listaProd)
            }

        } catch (e: Exception) {
            e.printStackTrace()

            Toast.makeText(this, "Escribí el dato que corresponda", Toast.LENGTH_SHORT).show()

        }
        limpiar()
    }




    private fun iniciar() {
        binding.btnGuardar.setOnClickListener {
            agregar()
        }


        binding.btneditar.setOnClickListener {
            editar()
        }
        binding.btnBuscar.setOnClickListener {

            buscar()
        }

        binding.btnBorrar.setOnClickListener(){
            borrar()
        }
    }

    private fun borrar() {
        try {
            with(binding) {
                val id: Int = etID.text.toString().toInt()
                var i = 0
                var borrado = false
                while (i < listaProd.size && !borrado) {
                    if (listaProd[i].id == id) {
                        listaProd.removeAt(i)
                        borrado = true
                    }
                    i++
                }
                if (borrado) {
                    rvProductos.layoutManager = LinearLayoutManager(this@MainActivity)
                    rvProductos.adapter = ProductoAdapter(listaProd)
                }
            }

        } catch (e: Exception) {
            Toast.makeText(
                this@MainActivity,
                "Escribí el ID correcto para borrar",
                Toast.LENGTH_SHORT
            ).show()
        }
        limpiar()
    }

    private fun editar() {

        try {
            with(binding) {
                val id: Int = etID.text.toString().toInt()
                var editado = false
                var i = 0

                while (i < listaProd.size && !editado) {

                    if (listaProd[i].id == id) {

                        listaProd[i].nombre = etNombreproducto.text.toString()
                        listaProd[i].precio = etPrecio.text.toString().toDouble()
                        editado = true
                    }
                    i++
                }
                if (editado) {

                    rvProductos.layoutManager = LinearLayoutManager(this@MainActivity)
                    rvProductos.adapter = ProductoAdapter(listaProd)
                }

            }
        } catch (e: Exception) {

            Toast.makeText(
                this@MainActivity,
                "Escribí un id correcto para editar",
                Toast.LENGTH_SHORT
            ).show()
        }
        limpiar()
    }

    private fun buscar() {

        try {
            with(binding) {
                val id: Int = etID.text.toString().toInt()

                var i = 0
                var found = false

                while (i < listaProd.size && !found) {

                    if (listaProd[i].id == id) {

                        etNombreproducto.setText(listaProd[i].nombre)
                        etPrecio.setText(listaProd[i].precio.toString())

                        found = true
                    }
                    i++
                }

            }

        } catch (e: Exception) {
            Toast.makeText(this, "Escribí el ID que corresponde al producto", Toast.LENGTH_SHORT)
                .show()
        }
    }
    private fun limpiar() {
        with(binding) {

            etID.setText("")
            etNombreproducto.setText("")
            etPrecio.setText("")
            etID.requestFocus()
        }
    }

}
