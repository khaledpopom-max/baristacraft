package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DrinkRepository
import com.example.model.Drink
import com.example.model.DrinkCategory
import com.example.ui.components.BaristaTimerDialog
import com.example.ui.components.BilingualBadge
import com.example.ui.components.DrinkCard
import com.example.ui.theme.*

@Composable
fun HomeScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToLearn: (DrinkCategory?) -> Unit,
    onNavigateToDrinkDetail: (String) -> Unit,
    onNavigateToCustomizer: () -> Unit,
    onNavigateToAiBarista: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showTimerDialog by remember { mutableStateOf(false) }
    val featuredDrink = remember { DrinkRepository.getDrinkById("iced-spanish-latte") ?: DrinkRepository.allDrinks.first() }

    if (showTimerDialog) {
        BaristaTimerDialog(
            initialSeconds = 28,
            onDismiss = { showTimerDialog = false }
        )
    }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onNavigateToAiBarista,
                containerColor = CoffeeCaramel,
                contentColor = Color.White,
                icon = {
                    Icon(
                        imageVector = Icons.Default.SmartToy,
                        contentDescription = "AI Barista"
                    )
                },
                text = {
                    Text(
                        text = "باريستا الذكاء الاصطناعي • AI Barista",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                },
                modifier = Modifier.testTag("home_ai_barista_fab")
            )
        },
        topBar = {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 2.dp,
                border = CardDefaults.outlinedCardBorder()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 20.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = CoffeeCaramel.copy(alpha = 0.15f),
                            modifier = Modifier.size(42.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.LocalCafe,
                                    contentDescription = "Logo",
                                    tint = CoffeeCaramel,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "باريستا كرافت",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = " • ",
                                    color = CoffeeCaramel,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "BaristaCraft",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = CoffeeCaramel
                                )
                            }
                            Text(
                                text = "دليل الباريستا المعتمد / Pro Barista Handbook",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    // Floating Timer Quick Trigger
                    IconButton(
                        onClick = { showTimerDialog = true },
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(CoffeeLatteCream.copy(alpha = 0.5f))
                            .testTag("home_quick_timer_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = "Shot Timer",
                            tint = CoffeeEspresso
                        )
                    }
                }
            }
        },
        modifier = modifier.testTag("home_screen")
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
        ) {
            // Hero Welcome Card with Café Gradient
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = CoffeeEspresso
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.horizontalGradient(
                                    listOf(CoffeeDark, CoffeeEspresso, CoffeeRoast)
                                )
                            )
                            .padding(20.dp)
                    ) {
                        Column {
                            BilingualBadge(
                                textAr = "أكاديمية تحضير المشروبات",
                                textEn = "Beverage Craft Academy",
                                color = CoffeeWarmAmber
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "أتقن فنون الباريستا الاحترافية",
                                fontSize = 21.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "Master Professional Barista Craftsmanship",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = CoffeeLatteCream
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "وصفات دقيقة بالغرام والميليلتر والمضخات • 5 أقسام رئيسية مع التعديلات ومؤقت الاستخلاص",
                                fontSize = 12.sp,
                                color = CoffeeLatteCream.copy(alpha = 0.85f),
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }

            // ==========================================
            // MANDATORY REQUIREMENT: TWO MAIN ACTION CARDS
            // 1. "بحث / Search"
            // 2. "تعلّم / Learn"
            // ==========================================
            item {
                Text(
                    text = "الأقسام الرئيسية • Main Actions",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // Action Card 1: "بحث / Search"
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(160.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable(onClick = onNavigateToSearch)
                            .testTag("home_search_action_card"),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        border = CardDefaults.outlinedCardBorder(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = CatIcedDrinks.copy(alpha = 0.15f),
                                modifier = Modifier.size(46.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search",
                                        tint = CatIcedDrinks,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }

                            Column {
                                Text(
                                    text = "بحث",
                                    fontSize = 19.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Search",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = CatIcedDrinks
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "ابحث عن أي مشروب أو طلب مخصص",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 1
                                )
                            }
                        }
                    }

                    // Action Card 2: "تعلّم / Learn"
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(160.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { onNavigateToLearn(null) }
                            .testTag("home_learn_action_card"),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        border = CardDefaults.outlinedCardBorder(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = CoffeeCaramel.copy(alpha = 0.15f),
                                modifier = Modifier.size(46.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.School,
                                        contentDescription = "Learn",
                                        tint = CoffeeCaramel,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }

                            Column {
                                Text(
                                    text = "تعلّم",
                                    fontSize = 19.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Learn",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = CoffeeCaramel
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "المنهج الدراسي: 5 أقسام للمشروبات",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }

            // Quick Tools Bar: Order Customizer & Shot Timer
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .clickable(onClick = onNavigateToCustomizer)
                        .testTag("home_order_builder_card"),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = CoffeeLatteCream.copy(alpha = 0.35f)
                    ),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = CoffeeCaramel,
                            modifier = Modifier.size(42.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Tune,
                                    contentDescription = "Customizer",
                                    tint = Color.White,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "مختبر تعديل الطلبات • Barista Order Lab",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = CoffeeDark
                            )
                            Text(
                                text = "عدّل الشوتات، المضخات، ونوع الحليب، واحصل على تذكرة تحضير دقيقة",
                                fontSize = 11.sp,
                                color = CoffeeRoast
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Open",
                            tint = CoffeeCaramel,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            // 5 Categories Quick Jump Row
            item {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "الأقسام الخمسة • The 5 Categories",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        TextButton(
                            onClick = { onNavigateToLearn(null) },
                            modifier = Modifier.testTag("home_view_all_categories_button")
                        ) {
                            Text(
                                text = "عرض الكل / View All",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = CoffeeCaramel
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        DrinkCategory.entries.forEach { cat ->
                            val count = remember(cat) { DrinkRepository.getDrinksByCategory(cat).size }
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .clickable { onNavigateToLearn(cat) }
                                    .testTag("home_category_chip_${cat.id}"),
                                shape = RoundedCornerShape(12.dp),
                                color = MaterialTheme.colorScheme.surface,
                                border = CardDefaults.outlinedCardBorder()
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 14.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = cat.nameAr,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = " • ",
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Text(
                                            text = cat.nameEn,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = MaterialTheme.colorScheme.secondary
                                        )
                                    }
                                    Text(
                                        text = "$count وصفات",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = CoffeeCaramel
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Featured Drink of the Day Card
            item {
                Text(
                    text = "مشروب اليوم المميّز • Featured Drink",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))
                DrinkCard(
                    drink = featuredDrink,
                    onClick = { onNavigateToDrinkDetail(featuredDrink.id) }
                )
            }

            // Daily Barista Pro Tip Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
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
                                imageVector = Icons.Default.Lightbulb,
                                contentDescription = "Tip",
                                tint = Color(0xFFF57F17),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "سر الباريستا الذهبي • Golden Barista Secret",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "معايرة الحليب: لا تدع حرارة التبخير تتجاوز 65°C أبداً، فالحرارة المرتفعة تكسر سكريات اللاكتوز وتلغي الحلاوة الطبيعية للحليب.",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 18.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Never steam milk above 65°C (150°F). Excessive heat breaks down natural lactose sugars, eliminating sweet creamy notes.",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            lineHeight = 15.sp
                        )
                    }
                }
            }
        }
    }
}
