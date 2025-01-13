package com.jsn.quickgrab.data.repository

import com.jsn.quickgrab.data.remote.model.Product
import javax.inject.Inject

class DataSourceRepository @Inject constructor(private val dataSource: DataSource) {
    suspend fun fetchAllProducts(): List<Product> {
        return try {
            dataSource.productsDao.getProducts()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun insertProduct(product: Product) {
        return try {
            dataSource.productsDao.addProduct(product)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun updateProduct(product: Product) {
        try {
            dataSource.productsDao.updateProduct(product)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun deleteProduct(productId: String) {
        try {
            dataSource.productsDao.deleteProduct(productId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun fetchProductById(productId: String): Product? {
        return try {
            dataSource.productsDao.getProductById(productId)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}