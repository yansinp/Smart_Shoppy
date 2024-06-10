package yansin.test.smartshoppy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import yansin.test.smartshoppy.data.local.dao.ShopDao
import yansin.test.smartshoppy.data.local.entity.AdvBannerEntity
import yansin.test.smartshoppy.data.local.entity.BannerEntity
import yansin.test.smartshoppy.data.local.entity.CategoryEntity
import yansin.test.smartshoppy.data.local.entity.FeaturedProductEntity
import yansin.test.smartshoppy.data.local.entity.MostPopularEntity

@Database(
    entities = [BannerEntity::class,
        FeaturedProductEntity::class,
        CategoryEntity::class,
        AdvBannerEntity::class,
        MostPopularEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ShopDatabase : RoomDatabase() {
    abstract val dao: ShopDao
}