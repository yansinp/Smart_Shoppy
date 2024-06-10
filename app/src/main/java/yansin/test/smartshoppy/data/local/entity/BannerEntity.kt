package yansin.test.smartshoppy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "banner_table")
data class BannerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title :String?,
    val image: String?
)
