package com.jsn.quickgrab.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jsn.quickgrab.data.db.entity.ProductEntity

@Dao
interface ProductDao {
    @Insert
    suspend fun insert(product: ProductEntity)

    @Query("SELECT * from products")
    suspend fun getAllProducts(): List<ProductEntity>
}