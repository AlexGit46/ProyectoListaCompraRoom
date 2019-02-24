package net.azarquiel.compraroom.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
@Dao
interface ProductoDao {

    @Query("SELECT * from producto ORDER BY nombre ASC")
    fun getAllProducts(): LiveData<List<Producto>>

    @Insert
    fun insert(producto: Producto)

    @Query("DELETE FROM producto WHERE id=:id")
    fun delete(id:Int)
}