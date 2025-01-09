package com.jsn.quickgrab.di.module

import android.content.Context
import androidx.room.Room
import com.jsn.quickgrab.data.db.AppDatabase
import com.jsn.quickgrab.data.db.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductsDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao()
    }
}