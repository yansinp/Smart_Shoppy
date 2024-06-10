package yansin.test.smartshoppy.data.remote

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import yansin.test.smartshoppy.core.util.Resource
import yansin.test.smartshoppy.data.local.dao.ShopDao
import yansin.test.smartshoppy.data.local.entity.AdvBannerEntity
import yansin.test.smartshoppy.data.mapper.toAdvBanner
import yansin.test.smartshoppy.data.mapper.toBanner
import yansin.test.smartshoppy.data.mapper.toBannerEntity
import yansin.test.smartshoppy.data.mapper.toCategoryEntity
import yansin.test.smartshoppy.data.mapper.toCategoryModel
import yansin.test.smartshoppy.data.mapper.toFeaturedProduct
import yansin.test.smartshoppy.data.mapper.toFeaturedProductEntity
import yansin.test.smartshoppy.data.mapper.toMostPopularEntity
import yansin.test.smartshoppy.data.mapper.toMostPopularProduct
import yansin.test.smartshoppy.domain.ShopRepository
import yansin.test.smartshoppy.domain.model.AdvBanner
import yansin.test.smartshoppy.domain.model.Banner
import yansin.test.smartshoppy.domain.model.Category
import yansin.test.smartshoppy.domain.model.FeaturedProduct
import yansin.test.smartshoppy.domain.model.MostPopularProduct
import yansin.test.smartshoppy.domain.model.ProductResponseModel
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val shopApi: ShopApi,
    private val shopDao: ShopDao
) : ShopRepository {

    suspend fun get(isNetworkAvailable: Boolean) {
        if (isNetworkAvailable) {
            val response = shopApi.getProductList()
            val bannerEntity =
                response.firstOrNull { it.title == BANNER }?.contents?.mapNotNull { it?.toBannerEntity() }
            val advBannerContent = response.firstOrNull { it.title == SINGLE_IMAGE }?.imageUrl
            val advBannerEntity = advBannerContent?.let { listOf(AdvBannerEntity(0, it)) }
            val categoryEntity =
                response.firstOrNull { it.title == CATEGORY }?.contents?.mapNotNull { it?.toCategoryEntity() }
            val featuredProductEntity =
                response.firstOrNull { it.title == FEATURED_PRODUCT }?.contents?.mapNotNull { it?.toFeaturedProductEntity() }
            val mostPopularEntity =
                response.firstOrNull { it.title == MOST_POPULAR }?.contents?.mapNotNull { it?.toMostPopularEntity() }

            shopDao.clearAllTables()
            if (!bannerEntity.isNullOrEmpty()) {
                shopDao.insertBanners(bannerEntity)
            } else {
                Log.d("Repository", "bannerEntity is null or empty")
            }

            if (!advBannerEntity.isNullOrEmpty()) {
                shopDao.insertAdvBanner(advBannerEntity)
            } else {
                Log.d("Repository", "advBannerEntity is null or empty")
            }

            if (!mostPopularEntity.isNullOrEmpty()) {
                shopDao.insertMostPopular(mostPopularEntity)
            } else {
                Log.d("Repository", "mostPopularEntity is null or empty")
            }

            if (!categoryEntity.isNullOrEmpty()) {
                shopDao.insertCategories(categoryEntity)
            } else {
                Log.d("Repository", "categoryEntity is null or empty")
            }

            if (!featuredProductEntity.isNullOrEmpty()) {
                shopDao.insertProducts(featuredProductEntity)
            } else {
                Log.d("Repository", "featuredProductEntity is null or empty")
            }
        }
    }


    override fun getProductList(
        isNetworkAvailable: Boolean
    ): Flow<Resource<List<ProductResponseModel>>> = flow {

        Log.d("API Call", "Repository Called")
        emit(Resource.Loading)

        /* if (isNetworkAvailable) {
             try {
                 val response = shopApi.getProductList()
                 Log.d("API Success", "Data: ${response}")
                 if (response.isNotEmpty()) {

                     val bannerEntity =
                         response.firstOrNull() { it.title == BANNER }?.contents?.map {
                             it?.toBannerEntity()
                         }
                     val categoryEntity =
                         response.firstOrNull() { it.title == CATEGORY }?.contents?.map {
                             it?.toCategoryEntity()
                         }
                     val productEntity =
                         response.firstOrNull() { it.title == FEATURED_PRODUCT }?.contents?.map {
                             it?.toProductEntity()
                         }
                     val advBannerEntity =
                         response.firstOrNull() { it.title == SINGLE_IMAGE }?.contents?.map {
                             it?.toProductEntity()
                         }



                     Log.d(
                         "Entities",
                         "Categories: $categoryEntity, Products: $productEntity, Banners: $bannerEntity"
                     )

                     shopDao.insertProducts(productEntity)
                     shopDao.insertCategories(categoryEntity)
                     shopDao.insertBanners(bannerEntity)


                 } else {
                     Log.d("API Call", "Empty response")
                     emit(Resource.Error(Constants.GENERAL_ERROR_MESSAGE))
                 }
             } catch (e: Exception) {
                 Log.e("API Exception", "Exception: ${e.message}")
                 emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE))
             }
         } else {
             Log.e("API Error", "No Network Available")
             emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE))
         }*/
    }

    override fun getBanners(): Flow<List<Banner>> {
        return shopDao.getBanners().map { it.map { it.toBanner() } }
    }

    override fun getAdvBanners(): Flow<List<AdvBanner>> {
        return shopDao.getAdvBanner().map { it.map { it.toAdvBanner() } }
    }

    override fun getCategory(): Flow<List<Category>> {
        return shopDao.getCategories().map { it.map { it.toCategoryModel() } }
    }

    override fun getMostPopularProducts(): Flow<List<MostPopularProduct>> {
        return shopDao.getMostPopular().map { it.map { it.toMostPopularProduct() } }
    }

    override fun getFeaturedProducts(): Flow<List<FeaturedProduct>> {
        return shopDao.getFeaturedProducts().map { it.map { it.toFeaturedProduct() } }
    }


    companion object {
        const val BANNER = "Top banner"
        const val MOST_POPULAR = "Best Sellers"
        const val SINGLE_IMAGE = "ad banner"
        const val CATEGORY = "Top categories"
        const val FEATURED_PRODUCT = "Most Popular"

    }
}
