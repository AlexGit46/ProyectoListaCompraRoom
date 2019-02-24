package net.azarquiel.compraroom.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class ProductoViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: ProductoRepository = ProductoRepository(application)

    var allProducts: LiveData<List<Producto>> = repository.allProdutcs

    fun insert(producto: Producto) {
        repository.insert(producto)
    }

    fun delete(id: Int) {
        repository.delete(id)
    }

}