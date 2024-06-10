package yansin.test.smartshoppy.data.mapper

import yansin.test.smartshoppy.data.local.entity.AdvBannerEntity
import yansin.test.smartshoppy.data.local.entity.BannerEntity
import yansin.test.smartshoppy.data.local.entity.CategoryEntity
import yansin.test.smartshoppy.data.local.entity.FeaturedProductEntity
import yansin.test.smartshoppy.data.local.entity.MostPopularEntity
import yansin.test.smartshoppy.data.remote.dto.Content
import yansin.test.smartshoppy.domain.model.AdvBanner
import yansin.test.smartshoppy.domain.model.Banner
import yansin.test.smartshoppy.domain.model.Category
import yansin.test.smartshoppy.domain.model.FeaturedProduct
import yansin.test.smartshoppy.domain.model.MostPopularProduct

fun CategoryEntity.toCategoryModel(): Category {
    return Category(
        productName = productName,
        productImage = productImage,
    )

}

fun BannerEntity.toBanner(): Banner {
    return Banner(
        title = title,
        imageUrl = image
    )
}
fun AdvBannerEntity.toAdvBanner(): AdvBanner {
    return AdvBanner(
        imageUrl = image
    )
}

fun FeaturedProductEntity.toFeaturedProduct(): FeaturedProduct {
    return FeaturedProduct(
        sku = sku,
        productName = productName,
        productImage = productImage,
        productRating = productRating,
        actualPrice = actualPrice,
        offerPrice = offerPrice,
        discount = discount
    )
}
fun MostPopularEntity.toMostPopularProduct(): MostPopularProduct {
    return MostPopularProduct(
        sku = sku,
        productName = productName,
        productImage = productImage,
        productRating = productRating,
        actualPrice = actualPrice,
        offerPrice = offerPrice,
        discount = discount
    )
}

fun Content.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(
        id = 0,
        productName = title,
        productImage = imageUrl
    )
}

fun Content.toBannerEntity(): BannerEntity {
    return BannerEntity(
        id = 0,
        title = title,
        image = imageUrl
    )
}

fun Content.toAdvBannerEntity(): AdvBannerEntity {
    return AdvBannerEntity(
        id = 0,
        image = imageUrl
    )
}

fun Content.toFeaturedProductEntity(): FeaturedProductEntity {
    return FeaturedProductEntity(
        id = 0,
        productName = productName,
        actualPrice = actualPrice,
        discount = discount,
        productImage = productImage,
        offerPrice = offerPrice,
        productRating = productRating.toString(),
        sku = sku
    )
}

fun Content.toMostPopularEntity(): MostPopularEntity {
    return MostPopularEntity(
        id = 0,
        productName = productName,
        actualPrice = actualPrice,
        discount = discount,
        productImage = productImage,
        offerPrice = offerPrice,
        productRating = productRating.toString(),
        sku = sku
    )
}



