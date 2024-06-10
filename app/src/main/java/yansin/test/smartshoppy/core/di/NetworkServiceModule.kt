package yansin.test.smartshoppy.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import yansin.test.smartshoppy.data.remote.ShopApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object NetworkServiceModule {
    @Provides
    @Singleton

    fun provideShopService(retrofit: Retrofit) : ShopApi{

        return retrofit.create(ShopApi :: class.java)
    }
}