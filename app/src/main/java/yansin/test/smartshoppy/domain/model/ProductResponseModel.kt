package yansin.test.smartshoppy.domain.model

data class ProductResponseModel(
    val category: List<Category>,
    val banner: List<Banner>,
    val advBanner: List<AdvBanner>,
    val mostPopularProduct: List<MostPopularProduct>,
    val featuredProduct: List<FeaturedProduct>
)
