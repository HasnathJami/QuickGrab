package com.jsn.quickgrab.data.repository

import com.jsn.quickgrab.data.remote.api.ApiService
import com.jsn.quickgrab.data.remote.firebase_db.firebase_dao.ProductFirebaseFireStoreDao
import javax.inject.Inject

class DataSource @Inject constructor(val apiService: ApiService, val productsDao: ProductFirebaseFireStoreDao)