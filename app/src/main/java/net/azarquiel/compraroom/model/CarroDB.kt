package net.azarquiel.compraroom.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room
import android.content.Context

@Database(entities = [Producto::class], version = 1)
abstract class CarroDB: RoomDatabase() {
    abstract fun productoDao(): ProductoDao
    companion object {
        @Volatile
        private var INSTANCE: CarroDB? = null

        fun getDatabase(context: Context): CarroDB? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        CarroDB::class.java, "carroDB"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}