package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
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
import com.example.ui.components.DrinkCard
import com.example.ui.theme.*

@Composable
fun LearnScreen(
    initialCategory: DrinkCategory? = null,
    onNavigateBack: () -> Unit,
    onNavigateToDrinkDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf(initialCategory ?: DrinkCategory.HOT) }

    val currentDrinks by remember(selectedCategory) {
        derivedStateOf {
            DrinkRepository.getDrinksByCategory(selectedCategory)
        }
    }

    val (categoryColor, categoryIcon) = when (selectedCategory) {
        DrinkCategory.HOT -> Pair(CatHotDrinks, Icons.Default.LocalCafe)
        DrinkCategory.ICED -> Pair(CatIcedDrinks, Icons.Default.AcUnit)
        DrinkCategory.JUICES -> Pair(CatJuices, Icons.Default.LocalDrink)
        DrinkCategory.SMOOTHIES -> Pair(CatSmoothies, Icons.Default.Icecream)
        DrinkCategory.MOJITOS -> Pair(CatMojitos, Icons.Default.Liquor)
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
                            modifier = Modifier.testTag("learn_back_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "منهج تعليم تحضير المشروبات",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Professional Beverage Curriculum",
                                fontSize = 12.sp,
                                color = CoffeeCaramel,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // 5 Categories Horizontal Tab Switcher
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        DrinkCategory.entries.forEach { category ->
                            val isSelected = category == selectedCategory
                            FilterChip(
                                selected = isSelected,
                                onClick = { selectedCategory = category },
                                leadingIcon = {
                                    Icon(
                                        imageVector = when (category) {
                                            DrinkCategory.HOT -> Icons.Default.LocalCafe
                                            DrinkCategory.ICED -> Icons.Default.AcUnit
                                            DrinkCategory.JUICES -> Icons.Default.LocalDrink
                                            DrinkCategory.SMOOTHIES -> Icons.Default.Icecream
                                            DrinkCategory.MOJITOS -> Icons.Default.Liquor
                                        },
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = "${category.nameAr} / ${category.nameEn}",
                                        fontSize = 12.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = CoffeeCaramel.copy(alpha = 0.2f),
                                    selectedLabelColor = MaterialTheme.colorScheme.onSurface
                                ),
                                modifier = Modifier.testTag("learn_tab_${category.id}")
                            )
                        }
                    }
                }
            }
        },
        modifier = modifier.testTag("learn_screen")
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
        ) {
            // Category Theory & Pro Barista Guidelines Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = categoryColor.copy(alpha = 0.12f)
                    ),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                shape = CircleShape,
                                color = categoryColor,
                                modifier = Modifier.size(36.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = categoryIcon,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = selectedCategory.nameAr,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "${selectedCategory.nameEn} • ${currentDrinks.size} Master Recipes",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = categoryColor
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Category specific master curriculum rule
                        val masterRule = when (selectedCategory) {
                            DrinkCategory.HOT -> Pair(
                                "قاعدة الباريستا الذهبية: تبخير الحليب عند 60-65°C بضغط بخار جاف ونسبة استخلاص 1:2 في 27-30 ثانية.",
                                "Golden Rule: Steam milk to 60-65°C using dry steam; pull espresso at 1:2 ratio within 27-30 seconds."
                            )
                            DrinkCategory.ICED -> Pair(
                                "قاعدة الباريستا الذهبية: استخدم الثلج الكريستالي الصلب لعزل الطبقات ولا تسكب الإسبريسو الساخن على الحليب مباشرة دون ثلج.",
                                "Golden Rule: Use solid crystal ice as barrier; pour espresso gently over ice cubes to preserve layered floating bands."
                            )
                            DrinkCategory.JUICES -> Pair(
                                "قاعدة الباريستا الذهبية: ترتيب الطبقات يتبع الكثافة والسكريات (الأثقل في القاع والأخف في الأعلى) باستخدام ظهر ملعقة الباريستا المقلوبة.",
                                "Golden Rule: Layering relies on specific gravity and brix density; pour slowly down inverted bar spoon."
                            )
                            DrinkCategory.SMOOTHIES -> Pair(
                                "قاعدة الباريستا الذهبية: لا تزد كمية الحليب لتفادي تمييع القوام، واستخدم الفواكه المجمدة بدلاً من الثلج الكثيف.",
                                "Golden Rule: Maintain 3 scoops ice cream to 120ml milk max; rely on frozen fruits to prevent watery consistency."
                            )
                            DrinkCategory.MOJITOS -> Pair(
                                "قاعدة الباريستا الذهبية: لا تفرم أوراق النعناع بشدة (صفقها لتنشيط الزيوت فقط)، واستخدم دائماً ثلجاً مجروشاً ناصعاً.",
                                "Golden Rule: Never over-muddle mint leaves to avoid releasing bitter chlorophyll; always use crushed ice."
                            )
                        }

                        Text(
                            text = masterRule.first,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 17.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = masterRule.second,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            lineHeight = 15.sp
                        )
                    }
                }
            }

            // Recipe List Section Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "الوصفات المقررة في هذا القسم • Section Curriculum",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${currentDrinks.size} وصفات",
                        fontSize = 12.sp,
                        color = categoryColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // List of drink cards in this category
            items(currentDrinks, key = { it.id }) { drink ->
                DrinkCard(
                    drink = drink,
                    onClick = { onNavigateToDrinkDetail(drink.id) }
                )
            }
        }
    }
}
