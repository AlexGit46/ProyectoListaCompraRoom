package net.azarquiel.compraroom.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "producto")
data class Producto(@PrimaryKey(autoGenerate = true)
                    @ColumnInfo(name = "id")
                    var id: Int?=0,
                    @ColumnInfo(name = "nombre")
                    var nombre:String="",
                    @ColumnInfo(name = "cantidad")
                    var cantidad:String="",
                    @ColumnInfo(name = "comprado")
                    var comprado:Boolean=false)
