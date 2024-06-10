package yansin.test.smartshoppy.presentation.home

import yansin.test.smartshoppy.data.remote.dto.ProductResponseDtoItem

data class HomeUiState (
    val shopData: List<ProductResponseDtoItem>? = emptyList(),
    val error: String = "",
    val loading: Boolean = false
)