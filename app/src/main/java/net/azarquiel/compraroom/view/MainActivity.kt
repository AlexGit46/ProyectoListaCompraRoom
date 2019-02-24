package net.azarquiel.compraroom.view

import android.arch.lifecycle.ViewModelProviders
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import net.azarquiel.compraroom.R
import net.azarquiel.compraroom.adapter.CustomAdapter
import net.azarquiel.compraroom.model.Producto
import net.azarquiel.compraroom.model.ProductoViewModel
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CustomAdapter
    private lateinit var productoViewModel: ProductoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        showData()
        productoViewModel = ViewModelProviders.of(this).get(ProductoViewModel::class.java)
        productoViewModel.allProducts.observe(this, Observer { products ->
            products?.let { adapter.setProducts(it) }
        })
        fab.setOnClickListener { addProducto() }

        title="Productos"

    }

    private fun showData() {
        adapter = CustomAdapter(this, R.layout.row)
        rvProductos.layoutManager = LinearLayoutManager(this)
        rvProductos.adapter = adapter
    }

    private fun addProducto() {
        alert {
            customView {
                title = "Add Producto"
                verticalLayout {
                    val etProducto = editText {
                        hint = "Producto"
                        padding = dip(20)
                    }
                    val etCantidad = editText {
                        hint = "Cantidad"
                        padding = dip(20)
                    }
                    positiveButton("Aceptar") {
                        if (TextUtils.isEmpty(etProducto.text) || TextUtils.isEmpty(etCantidad.text))
                            longToast("Todos los campos son obligatorios...")
                        else
                            productoViewModel.insert(Producto(null, etProducto.text.toString(), etCantidad.text.toString(), false))
                    }
                    negativeButton("Cancelar") {
                    }
                }
            }
        }.show()
    }

    fun onClickProducto(view: View) {
        val producto = view.tag as Producto
        alert("¿Seguro eliminar ${producto.nombre}?", "Confirm") {
            yesButton {productoViewModel.delete(producto.id!!) }
            noButton {}
        }.show()

    }

}
