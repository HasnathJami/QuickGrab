package com.jsn.quickgrab.data.remote.firebase_db.firebase_dao

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.jsn.quickgrab.data.remote.model.Product
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductFirebaseFireStoreDao @Inject constructor(firebaseFireStore: FirebaseFirestore) {

    private val TAG: String by lazy { this.javaClass.simpleName }
    private val productCollection by lazy { firebaseFireStore.collection("products") }
    suspend fun addProduct(product: Product) {
        try {
            val docRef = productCollection.document(product._id)
            docRef.set(product).await()
            Log.d(TAG, "Item added successfully!")
        } catch (e: Exception) {
            Log.w(TAG, "Error adding item", e)
        }

    }

    suspend fun getProducts(): List<Product> {
        return try {
            val documents = productCollection.get().await()
            documents.map { document ->
                document.toObject(Product::class.java).apply {
                    _id = document.id
                }
            }
        } catch (e: Exception) {
            Log.w(TAG, "Error getting items", e)
            emptyList() // Return an empty list on failure
        }
    }

    suspend fun getProductById(productId: String): Product? {
        return try {
            val documents = productCollection.document(productId).get().await()
            if (documents.exists()) {
                val product = documents.toObject(Product::class.java)
                product?._id = documents.id
                product
            } else {
                Log.w("TAG", "Item not found")
                null // Return null if the document doesn't exist
            }
        } catch (e: Exception) {
            Log.w("TAG", "Error getting item by ID", e)
            null // Return null in case of error
        }
    }

    suspend fun updateProduct(product: Product) {
        try {
            productCollection.document(product._id).set(product).await()
            Log.d("TAG", "Item updated successfully!")
        } catch (e: Exception) {
            Log.w("TAG", "Error updating item", e)
        }
    }

    suspend fun deleteProduct(productId: String) {
        try {
            productCollection.document(productId).delete().await()
            Log.d("TAG", "Item deleted successfully!")
        } catch (e: Exception) {
            Log.w("TAG", "Error deleting item", e)
        }

    }
}