package yansin.test.smartshoppy.domain

import kotlinx.coroutines.flow.Flow
import yansin.test.smartshoppy.core.util.Resource
import yansin.test.smartshoppy.domain.model.AdvBanner
import yansin.test.smartshoppy.domain.model.Banner
import yansin.test.smartshoppy.domain.model.Category
import yansin.test.smartshoppy.domain.model.FeaturedProduct
import yansin.test.smartshoppy.domain.model.MostPopularProduct
import yansin.test.smartshoppy.domain.model.ProductResponseModel


interface ShopRepository {

    fun getProductList(
        isNetworkAvailable: Boolean
    ) : Flow<Resource<List<ProductResponseModel>>>

    fun getBanners():Flow<List<Banner>>
    fun getAdvBanners():Flow<List<AdvBanner>>
    fun getCategory():Flow<List<Category>>
    fun getMostPopularProducts():Flow<List<MostPopularProduct>>
    fun getFeaturedProducts():Flow<List<FeaturedProduct>>
}