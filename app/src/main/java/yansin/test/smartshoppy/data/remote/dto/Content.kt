package yansin.test.smartshoppy.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Content(
    @Json(name = "title")
    val title: String?,
    @Json(name = "image_url")
    val imageUrl: String?,
    @Json(name = "sku")
    val sku: String?,
    @Json(name = "product_name")
    val productName: String?,
    @Json(name = "product_image")
    val productImage: String?,
    @Json(name = "product_rating")
    val productRating: Int?,
    @Json(name = "actual_price")
    val actualPrice: String?,
    @Json(name = "offer_price")
    val offerPrice: String?,
    @Json(name = "discount")
    val discount: String?
) : Parcelable