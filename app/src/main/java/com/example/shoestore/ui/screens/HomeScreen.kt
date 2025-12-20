// ui/screens/HomeScreen.kt
package com.example.shoestore.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.shoestore.R
import com.example.shoestore.data.model.Category
import com.example.shoestore.data.model.Product
import com.example.shoestore.ui.components.ProductCard
import com.example.shoestore.ui.theme.AppTypography
import com.example.shoestore.ui.theme.ShoeShopTheme
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCartClick: () -> Unit,
    onSettingsClick: () -> Unit = {},
    onProfileEditClick: () -> Unit = {},
    onProfileLogoutClick: () -> Unit = {},
    onProductClick: (String) -> Unit = {} // Обратите внимание: теперь String вместо UUID
) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    var selectedCategory by remember { mutableStateOf("All") }

    // Тестовые данные для демонстрации
    val categories = listOf(
        Category("All"),
        Category("Outdoor"),
        Category("Tennis"),
        Category("Men"),
        Category("Women")
    )

    // Тестовые продукты
    val testProducts = listOf(
        Product(
            id = "1",
            name = "PUMA CA Pro Classic",
            price = "13999 ₽",
            originalPrice = "15999 ₽",
            category = "BEST SELLER",
            description = "Ретро-кроссовки в стиле баскетбола",
            imageResId = R.drawable._e9c9924_7000_4e7b_8332_33bd8d0bd9b6_f8f32b
        ),
        Product(
            id = "2",
            name = "STREETBEAT Plimsoll Retro",
            price = "8499 ₽",
            originalPrice = "9999 ₽",
            category = "NEW",
            description = "Ностальгические кеды в стиле ретро",
            imageResId = R.drawable._e9c9924_7000_4e7b_8332_33bd8d0bd9b6_f8f32b
        ),
        Product(
            id = "3",
            name = "Adidas Niteball",
            price = "19499 ₽",
            originalPrice = "21999 ₽",
            category = "BEST SELLER",
            description = "Беговые кроссовки с инновационной амортизацией",
            imageResId = R.drawable._e9c9924_7000_4e7b_8332_33bd8d0bd9b6_f8f32b
        ),
        Product(
            id = "4",
            name = "PUMA Velophasis Phased",
            price = "16999 ₽",
            originalPrice = "18999 ₽",
            category = "NEW",
            description = "Минималистичные повседневные кроссовки",
            imageResId = R.drawable._e9c9924_7000_4e7b_8332_33bd8d0bd9b6_f8f32b
        ),
        Product(
            id = "5",
            name = "HUGO Leon Run",
            price = "19999 ₽",
            originalPrice = "22999 ₽",
            category = "BEST SELLER",
            description = "Кроссовки в уличном стиле",
            imageResId = R.drawable._e9c9924_7000_4e7b_8332_33bd8d0bd9b6_f8f32b
        ),
        Product(
            id = "6",
            name = "Adidas Ozmillen",
            price = "20599 ₽",
            originalPrice = "23999 ₽",
            category = "NEW",
            description = "Легкие и дышащие беговые кроссовки",
            imageResId = R.drawable._e9c9924_7000_4e7b_8332_33bd8d0bd9b6_f8f32b
        )
    )

    ShoeShopTheme {
        Scaffold(
            bottomBar = {
                Box(
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.vector_1789),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize()
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row {
                            IconButton(onClick = { selectedTab = 0 }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.home),
                                    contentDescription = stringResource(R.string.home),
                                    tint = if (selectedTab == 0) MaterialTheme.colorScheme.primary else Color.Black
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            IconButton(onClick = { selectedTab = 1 }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.favorite),
                                    contentDescription = stringResource(R.string.favourite),
                                    tint = if (selectedTab == 1) MaterialTheme.colorScheme.primary else Color.Black
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .offset(y = (-20).dp)
                                .size(56.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            FloatingActionButton(
                                onClick = { onCartClick() },
                                modifier = Modifier.size(56.dp),
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary,
                                shape = CircleShape
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.bag_2),
                                    contentDescription = stringResource(R.string.cart),
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        Row {
                            IconButton(onClick = { selectedTab = 2 }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.notification),
                                    contentDescription = stringResource(R.string.notifications),
                                    tint = if (selectedTab == 2) MaterialTheme.colorScheme.primary else Color.Black
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            IconButton(onClick = { selectedTab = 3 }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.profile),
                                    contentDescription = stringResource(R.string.profile),
                                    tint = if (selectedTab == 3) MaterialTheme.colorScheme.primary else Color.Black
                                )
                            }
                        }
                    }
                }
            },
            containerColor = Color(0xFFF7F7F9)
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color(0xFFF7F7F9))
            ) {
                when (selectedTab) {
                    0 -> HomeTabContent(
                        categories = categories,
                        selectedCategory = selectedCategory,
                        onCategorySelected = { selectedCategory = it },
                        onSettingsClick = onSettingsClick,
                        products = testProducts,
                        onProductClick = onProductClick
                    )
                    1 -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.favourite),
                                style = AppTypography.headingRegular32
                            )
                        }
                    }
                    2 -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.notifications),
                                style = AppTypography.headingRegular32
                            )
                        }
                    }
                    3 -> {
                        ProfileScreen(
                            onEditClick = onProfileEditClick,
                            onBackClick = { selectedTab = 0 },
                            onLogoutClick = onProfileLogoutClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeTabContent(
    categories: List<Category>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    onSettingsClick: () -> Unit,
    products: List<Product>,
    onProductClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.home),
            style = AppTypography.headingRegular32,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.search),
                            style = AppTypography.bodyRegular14
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.search),
                            tint = Color.Gray
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable { onSettingsClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sliders),
                    contentDescription = stringResource(R.string.settings),
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item {
            CategorySection(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = onCategorySelected
            )
        }

        item {
            PopularSection(
                products = products.filter { it.category == "BEST SELLER" },
                onProductClick = onProductClick
            )
        }

        item {
            AllProductsSection(
                products = products,
                onProductClick = onProductClick
            )
        }

        item { PromotionsSection() }
    }
}

@Composable
private fun CategorySection(
    categories: List<Category>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    Column {
        Text(
            text = stringResource(id = R.string.categories),
            style = AppTypography.bodyMedium16
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(categories) { category ->
                CategoryChip(
                    category = category.name,
                    isSelected = selectedCategory == category.name,
                    onClick = { onCategorySelected(category.name) }
                )
            }
        }
    }
}

@Composable
private fun CategoryChip(
    category: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clickable { onClick() }
            .clip(RoundedCornerShape(16.dp)),
        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.White,
        contentColor = if (isSelected) Color.White else Color.Black
    ) {
        Text(
            text = category,
            style = AppTypography.bodyMedium16.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
private fun PopularSection(
    products: List<Product>,
    onProductClick: (String) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.popular),
                style = AppTypography.bodyMedium16
            )
            Text(
                text = "Все",
                style = AppTypography.bodyRegular12,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        if (products.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(Color.White, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp)
                    )
                    Text(
                        text = "Популярные товары",
                        style = AppTypography.bodyRegular16,
                        color = Color.Gray
                    )
                }
            }
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(products) { product ->
                    ProductCard(
                        product = product,
                        onProductClick = { onProductClick(product.id) },
                        onFavoriteClick = {
                            // Обработка добавления/удаления из избранного
                            println("Избранное: ${product.name}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun AllProductsSection(
    products: List<Product>,
    onProductClick: (String) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Все товары",
                style = AppTypography.bodyMedium16
            )
            Text(
                text = "${products.size} товаров",
                style = AppTypography.bodyRegular12,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        if (products.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.White, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Товары не найдены",
                    style = AppTypography.bodyRegular16,
                    color = Color.Gray
                )
            }
        } else {
            // Используем FlowRow или Grid для отображения сетки
            // Покажем в виде горизонтальных рядов
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Разделим товары на пары для отображения в 2 колонки
                products.chunked(2).forEach { rowProducts ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        rowProducts.forEach { product ->
                            Box(modifier = Modifier.weight(1f)) {
                                ProductCard(
                                    product = product,
                                    onProductClick = { onProductClick(product.id) },
                                    onFavoriteClick = {
                                        // Обработка добавления/удаления из избранного
                                        println("Избранное: ${product.name}")
                                    }
                                )
                            }
                        }
                        // Если в ряду только один товар, добавляем пустой Box для выравнивания
                        if (rowProducts.size == 1) {
                            Box(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PromotionsSection() {
    Column {
        Text(
            text = stringResource(id = R.string.sales),
            style = AppTypography.bodyMedium16,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.frame_1000000849),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillWidth
        )
    }
}