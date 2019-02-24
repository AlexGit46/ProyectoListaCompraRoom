package net.azarquiel.compraroom.model

import android.app.Application
import android.arch.lifecycle.LiveData
import org.jetbrains.anko.doAsync


class ProductoRepository(application: Application) {

    val productoDao = CarroDB.getDatabase(application)!!.productoDao()

    val allProdutcs: LiveData<List<Producto>> = productoDao.getAllProducts()

    fun insert(producto: Producto) {
        doAsync {
            productoDao.insert(producto)
        }
    }

    fun delete(id:Int) {
        doAsync {
            productoDao.delete(id)
        }
    }
}