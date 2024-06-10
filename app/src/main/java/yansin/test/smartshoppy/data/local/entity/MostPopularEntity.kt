package yansin.test.smartshoppy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "featured_products_table")
data class MostPopularEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val sku : String?,
    val productName : String?,
    val productImage : String?,
    val productRating : String?,
    val actualPrice : String?,
    val offerPrice : String?,
    val discount : String?
)