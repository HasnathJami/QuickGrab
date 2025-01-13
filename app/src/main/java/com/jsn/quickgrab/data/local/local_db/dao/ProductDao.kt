package com.jsn.quickgrab.data.local.local_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jsn.quickgrab.data.local.local_db.entity.ProductEntity

@Dao
interface ProductDao {
    @Insert
    suspend fun insert(product: ProductEntity)

    @Query("SELECT * from products")
    suspend fun getAllProducts(): List<ProductEntity>
}