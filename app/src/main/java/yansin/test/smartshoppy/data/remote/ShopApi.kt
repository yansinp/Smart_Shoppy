package yansin.test.smartshoppy.data.remote

import retrofit2.http.GET
import yansin.test.smartshoppy.data.remote.dto.ProductResponseDtoItem

interface ShopApi {
    @GET("Todo")
    suspend fun getProductList(): List<ProductResponseDtoItem>
}
