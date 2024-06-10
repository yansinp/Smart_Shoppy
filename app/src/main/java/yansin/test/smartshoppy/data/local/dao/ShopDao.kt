package yansin.test.smartshoppy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import yansin.test.smartshoppy.data.local.entity.AdvBannerEntity
import yansin.test.smartshoppy.data.local.entity.BannerEntity
import yansin.test.smartshoppy.data.local.entity.CategoryEntity
import yansin.test.smartshoppy.data.local.entity.FeaturedProductEntity
import yansin.test.smartshoppy.data.local.entity.MostPopularEntity

@Dao
interface ShopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanners(banners: List<BannerEntity?>?)

    @Query("SELECT * FROM banner_table")
    fun getBanners(): Flow<List<BannerEntity>>

    @Query("DELETE FROM banner_table")
    suspend fun clearBanners()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity?>?)

    @Query("SELECT * FROM category_table")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Query("DELETE FROM category_table")
    suspend fun clearCategories()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<FeaturedProductEntity?>?)

    @Query("SELECT * FROM product_table")
    fun getFeaturedProducts(): Flow<List<FeaturedProductEntity>>

    @Query("DELETE FROM product_table")
    suspend fun clearProducts()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdvBanner(products: List<AdvBannerEntity?>?)

    @Query("SELECT * FROM adv_banner_table")
    fun getAdvBanner(): Flow<List<AdvBannerEntity>>

    @Query("DELETE FROM adv_banner_table")
    suspend fun clearAdvBanner()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMostPopular(products: List<MostPopularEntity?>?)

    @Query("SELECT * FROM featured_products_table")
    fun getMostPopular(): Flow<List<MostPopularEntity>>

    @Query("DELETE FROM featured_products_table")
    suspend fun clearMostPopular()

    @Transaction
    suspend fun clearAllTables() {
        clearBanners()
        clearCategories()
        clearProducts()
        clearAdvBanner()
        clearMostPopular()
    }


}