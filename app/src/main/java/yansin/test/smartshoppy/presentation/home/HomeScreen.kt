package yansin.test.smartshoppy.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import yansin.test.smartshoppy.core.theme.lightGreen
import yansin.test.smartshoppy.domain.model.Banner
import yansin.test.smartshoppy.domain.model.Category
import yansin.test.smartshoppy.domain.model.FeaturedProduct
import yansin.test.smartshoppy.domain.model.MostPopularProduct

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navController: NavController, uiState: HomeUiState
) {
    val shopViewModel = viewModel<HomeViewModel>()
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val pagerState = rememberPagerState()
    val bannerData by shopViewModel.bannerData.collectAsState(initial = emptyList())
    val advBannerData by shopViewModel.advBannerData.collectAsState(initial = emptyList())
    val categoryData by shopViewModel.categoryData.collectAsState(initial = emptyList())
    val mostPopularData by shopViewModel.mostPopularData.collectAsState(initial = emptyList())
    val featuredProductData by shopViewModel.featuredProductData.collectAsState(initial = emptyList())

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        if (uiState.loading) {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
            ) {
                Header(
                    text,
                    active,
                    onQueryChange = { text = it },
                    onSearch = { active = false },
                    onActiveChange = { active = it })

                BannerSection(bannerData, pagerState)

                SectionTitle("Most Popular")

                MostPopularSection(mostPopularData)

                AsyncImage(
                    model = advBannerData.map { it.imageUrl }.firstOrNull(),
                    contentDescription = "",
                    modifier = Modifier
                        .height(150.dp)
                        .padding(bottom = 16.dp, top = 16.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                SectionTitle("Categories")

                CategorySection(categoryData)

                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle("Featured Products")

                FeaturedProductsSection(featuredProductData)
            }
        }
    }
}

@Composable
fun CategorySection(data: List<Category>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(data) { item ->
            CategoryItem(item)
        }
    }
}

@Composable
fun FeaturedProductsSection(data: List<FeaturedProduct>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(data) { item ->
            FeaturedProducts(item)
        }
    }
}


@Composable
fun MostPopularSection(data: List<MostPopularProduct>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(data) { item ->
            ProductItem(item)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    text: String,
    active: Boolean,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onActiveChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(top = 50.dp)
            .background(lightGreen), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.ShoppingCart,
            contentDescription = "Shopping Cart",
            modifier = Modifier
                .padding(start = 16.dp, top = 20.dp, end = 16.dp)
                .clickable { }
        )
        SearchBar(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            query = text,
            onQueryChange = onQueryChange,
            onSearch = { },
            active = active,
            onActiveChange = onActiveChange,
            placeholder = {
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = "Search Icon"
                )
            },
            trailingIcon = {
            }
        ) {
        }
        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Notifications",
            modifier = Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerSection(bannerData: List<Banner>, pagerState: PagerState) {
    HorizontalPager(
        count = bannerData.size,
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { page ->
        val banner = bannerData[page]
        BannerItem(banner)
    }
}


@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 8.dp, start = 16.dp, top = 8.dp)
    )
}

@Composable
fun BannerItem(banner: Banner) {
    AsyncImage(
        model = banner.imageUrl,
        contentDescription = banner.title,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
    )
}

@Composable
fun ProductItem(product: MostPopularProduct) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .height(330.dp), elevation = 4.dp
    ) {
        Column {
            Box(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(1.5.dp, Color.Gray, RoundedCornerShape(16.dp))
                )

                AsyncImage(
                    model = product.productImage,
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                        .padding(top = 2.dp, start = 5.dp, end = 5.dp)
                )
                if (!(product.discount?.split("%")?.get(0)
                        ?.toInt()!! > 0 && product.actualPrice == product.offerPrice)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp)
                            .background(Color.Red)
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = product.discount.toString(),
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = product.productName.toString(),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        fontWeight = FontWeight.Light,
                        color = Color.Black,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = product.actualPrice.toString(),
                            textDecoration = TextDecoration.LineThrough,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        // Offer Price
                        Text(
                            text = product.offerPrice.toString(), fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItem(itemData: Category) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .height(260.dp), elevation = 4.dp
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(1.5.dp, Color.Gray, RoundedCornerShape(16.dp))
            ) {
                AsyncImage(
                    model = itemData.productImage,
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                        .padding(top = 2.dp, start = 5.dp, end = 5.dp)
                )
                Text(
                    text = itemData.productName.toString(),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun FeaturedProducts(product: FeaturedProduct) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .height(330.dp)
            .padding(bottom = 24.dp), elevation = 4.dp
    ) {
        Column {
            Box(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(1.5.dp, Color.Gray, RoundedCornerShape(16.dp))
                )

                AsyncImage(
                    model = product.productImage,
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                        .padding(top = 2.dp, start = 5.dp, end = 5.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(8.dp)
                        .height(8.dp)
                )
                if (!(product.discount?.split("%")?.get(0)
                        ?.toInt()!! > 0 && product.actualPrice == product.offerPrice)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp)
                            .background(Color.Red)
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = product.discount.toString(),
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .width(8.dp)
                        .height(8.dp)
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = product.productName.toString(),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        fontWeight = FontWeight.Light,
                        color = Color.Black,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 15.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = product.actualPrice.toString(),
                            textDecoration = TextDecoration.LineThrough,
                            color = Color.Gray
                        )
                        Spacer(
                            modifier = Modifier
                                .width(8.dp)
                                .height(8.dp)
                        )
                        Text(
                            text = product.offerPrice.toString(), fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(bottom = 8.dp)
    ) {
        BottomNavItem.values().forEach { item ->
            BottomNavigationItem(
                modifier = Modifier
                    .background(color = Color.Gray),
                selected = false,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.label) }
            )
        }
    }
}

enum class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    Home("home", Icons.Default.Home, "Home"),
    Cart("cart", Icons.Default.ShoppingCart, "Cart"),
    Profile("profile", Icons.Default.Person, "Profile"),
}
