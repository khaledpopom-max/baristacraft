package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DrinkRepository
import com.example.model.DrinkCategory
import com.example.ui.components.BilingualBadge
import com.example.ui.components.DrinkCard
import com.example.ui.theme.CoffeeCaramel
import com.example.ui.theme.CoffeeLatteCream

@Composable
fun SearchScreen(
    initialQuery: String = "",
    onNavigateBack: () -> Unit,
    onNavigateToDrinkDetail: (String) -> Unit,
    onNavigateToCustomizer: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf(initialQuery) }
    var selectedCategory by remember { mutableStateOf<DrinkCategory?>(null) }

    val searchResults by remember(searchQuery, selectedCategory) {
        derivedStateOf {
            DrinkRepository.searchDrinks(searchQuery, selectedCategory)
        }
    }

    val customOrderAnalysis by remember(searchQuery) {
        derivedStateOf {
            DrinkRepository.analyzeCustomOrder(searchQuery)
        }
    }

    Scaffold(
        topBar = {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 2.dp,
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    // Back & Title
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onNavigateBack,
                            modifier = Modifier.testTag("search_back_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "بحث عن المشروبات والطلبات",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Search Drinks & Custom Orders",
                                fontSize = 12.sp,
                                color = CoffeeCaramel,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Real-Time Search Bar
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("search_text_input"),
                        placeholder = {
                            Text(
                                text = "ابحث مثلاً: 'Iced Spanish Latte Extra Caramel' أو 'كرك'",
                                fontSize = 13.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Icon",
                                tint = CoffeeCaramel
                            )
                        },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(
                                    onClick = { searchQuery = "" },
                                    modifier = Modifier.testTag("search_clear_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Clear"
                                    )
                                }
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = CoffeeCaramel,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Category Filter Chips
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChip(
                            selected = selectedCategory == null,
                            onClick = { selectedCategory = null },
                            label = { Text("الكل / All") },
                            modifier = Modifier.testTag("filter_all")
                        )
                        DrinkCategory.entries.forEach { category ->
                            FilterChip(
                                selected = selectedCategory == category,
                                onClick = {
                                    selectedCategory = if (selectedCategory == category) null else category
                                },
                                label = { Text("${category.nameAr} / ${category.nameEn}") },
                                modifier = Modifier.testTag("filter_${category.id}")
                            )
                        }
                    }
                }
            }
        },
        modifier = modifier.testTag("search_screen")
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
        ) {
            // Smart Custom Order Detection Card (e.g. "Iced Spanish Latte Extra Caramel")
            if (customOrderAnalysis != null && (customOrderAnalysis?.customModifiersAr?.isNotEmpty() == true)) {
                item {
                    val analysis = customOrderAnalysis!!
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("custom_order_detected_card"),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = CoffeeLatteCream.copy(alpha = 0.45f)
                        ),
                        border = CardDefaults.outlinedCardBorder()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Tune,
                                    contentDescription = "Custom Order",
                                    tint = CoffeeCaramel,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "تم التعرّف على طلب مخصص • Custom Order Detected",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "المشروب الأساسي: ${analysis.matchedDrink.nameAr} (${analysis.matchedDrink.nameEn})",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "تعديلات الباريستا المطلوبة بدقة:",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = CoffeeCaramel
                            )
                            analysis.customModifiersAr.zip(analysis.customModifiersEn).forEach { (ar, en) ->
                                Text(
                                    text = "• $ar / $en",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Button(
                                onClick = { onNavigateToDrinkDetail(analysis.matchedDrink.id) },
                                colors = ButtonDefaults.buttonColors(containerColor = CoffeeCaramel),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("open_customized_recipe_button")
                            ) {
                                Text(
                                    text = "عرض الوصفة كاملة بالتعديل • Open Recipe with Modifiers",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            // Quick Preset Search Suggestions (if search is empty)
            if (searchQuery.isEmpty()) {
                item {
                    Column {
                        Text(
                            text = "اقتراحات بحث سريعة • Quick Suggestions",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val suggestions = listOf(
                                "Iced Spanish Latte Extra Caramel",
                                "شاي كرك بالزعفران",
                                "Virgin Classic Mojito",
                                "Oreo Milkshake",
                                "سحلب بالمكسرات",
                                "كوكتيل إمبراطور طبقات",
                                "Flat White"
                            )
                            suggestions.forEach { suggestion ->
                                SuggestionChip(
                                    onClick = { searchQuery = suggestion },
                                    label = { Text(suggestion, fontSize = 12.sp) },
                                    modifier = Modifier.testTag("suggestion_${suggestion.take(8)}")
                                )
                            }
                        }
                    }
                }
            }

            // Results Count Bar
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "نتائج المشروبات (${searchResults.size}) • Matching Drinks",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (selectedCategory != null) {
                        Text(
                            text = "${selectedCategory?.nameAr}",
                            fontSize = 12.sp,
                            color = CoffeeCaramel,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            // Empty State
            if (searchResults.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "No Results",
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "لم يتم العثور على مشروبات مطابقة",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "No matching drinks found for your query",
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.height(14.dp))
                            Button(
                                onClick = {
                                    searchQuery = ""
                                    selectedCategory = null
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = CoffeeCaramel)
                            ) {
                                Text("إعادة الضبط / Reset Search")
                            }
                        }
                    }
                }
            } else {
                items(searchResults, key = { it.id }) { drink ->
                    DrinkCard(
                        drink = drink,
                        onClick = { onNavigateToDrinkDetail(drink.id) }
                    )
                }
            }
        }
    }
}
