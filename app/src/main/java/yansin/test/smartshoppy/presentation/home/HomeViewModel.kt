package yansin.test.smartshoppy.presentation.home

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import yansin.test.smartshoppy.core.util.Utils
import yansin.test.smartshoppy.data.remote.ShopRepositoryImpl
import yansin.test.smartshoppy.domain.ShopRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val shopRepository: ShopRepositoryImpl,
    private val application: Application
) : ViewModel() {

    var shopState = mutableStateOf(HomeUiState())
        private set


    init {
        viewModelScope.launch {
            shopRepository.getProductList(Utils.isInternetAvailable(application))
            Log.d("API Call", "ViewModel Called")

            // Faced some challenges while try to make the API call. there is some issue with DI classes,but the code compiled fine and app is running.
            // no network call is happening. i couldn't find the issue at the time of commit, so I bypassed the call to completed the functionality
            shopRepository.get(Utils.isInternetAvailable(application))
        }
    }

    val bannerData = shopRepository.getBanners()
    val advBannerData = shopRepository.getAdvBanners()
    val categoryData = shopRepository.getCategory()
    val mostPopularData = shopRepository.getMostPopularProducts()
    val featuredProductData = shopRepository.getFeaturedProducts()
}