package yansin.test.smartshoppy.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class ProductResponseDtoItem(
    @Json(name = "type")
    val type: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "contents")
    val contents: List<Content?>?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "image_url")
    val imageUrl: String?
) : Parcelable