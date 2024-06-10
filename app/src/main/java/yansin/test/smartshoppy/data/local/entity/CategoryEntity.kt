package yansin.test.smartshoppy.data.local.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val productName : String?,
    val productImage : String?
)
