package com.jsn.quickgrab.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val _id: Int,
    val id: Int,
    val name: String,
    val price: Double
)
