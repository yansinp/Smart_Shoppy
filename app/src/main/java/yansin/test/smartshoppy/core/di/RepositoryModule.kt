package yansin.test.smartshoppy.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yansin.test.smartshoppy.data.local.dao.ShopDao
import yansin.test.smartshoppy.data.remote.ShopApi
import yansin.test.smartshoppy.data.remote.ShopRepositoryImpl
import yansin.test.smartshoppy.domain.ShopRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object RepositoryModule {
    @Provides
    @Singleton

    fun provideShopRepository(
        shopApi: ShopApi,
        shopDao: ShopDao
    ): ShopRepository{
        return ShopRepositoryImpl(shopApi,shopDao)
    }
}