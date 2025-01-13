package com.jsn.quickgrab.data.local.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jsn.quickgrab.data.local.local_db.dao.ProductDao
import com.jsn.quickgrab.data.local.local_db.entity.ProductEntity

@Database([ProductEntity::class], version = 1) // other entities, version should be changed if we work on schema
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    // other dao's

}