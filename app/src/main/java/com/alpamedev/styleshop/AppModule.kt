package com.alpamedev.styleshop

import android.content.Context
import com.alpamedev.data.ProductLocalDataSource
import com.alpamedev.data.ProductRemoteDataSource
import com.alpamedev.styleshop.framework.api.RetrofitConfig
import com.alpamedev.styleshop.framework.local.ProductLocalDB
import com.alpamedev.styleshop.framework.remote.ProductRemoteDB
import com.alpamedev.styleshop.framework.room.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideProductRemoteDataSource(): ProductRemoteDataSource{
        return ProductRemoteDB(RetrofitConfig)
    }

    @Provides
    fun provideProductLocalDataSource(@ApplicationContext context: Context): ProductLocalDataSource {
        return ProductLocalDB(ProductDatabase.getInstance(context).getProductDao())
    }
}