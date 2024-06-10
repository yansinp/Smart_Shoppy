package yansin.test.smartshoppy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "adv_banner_table")
data class AdvBannerEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image: String?
)