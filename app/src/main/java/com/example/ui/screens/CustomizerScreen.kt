package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DrinkRepository
import com.example.model.CupSize
import com.example.model.Drink
import com.example.ui.theme.CoffeeCaramel
import com.example.ui.theme.CoffeeDark
import com.example.ui.theme.CoffeeEspresso
import com.example.ui.theme.CoffeeLatteCream

@Composable
fun CustomizerScreen(
    onNavigateBack: () -> Unit,
    onNavigateToDrinkDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val candidateDrinks = remember {
        listOf(
            DrinkRepository.getDrinkById("iced-spanish-latte")!!,
            DrinkRepository.getDrinkById("karak-tea")!!,
            DrinkRepository.getDrinkById("hot-spanish-latte")!!,
            DrinkRepository.getDrinkById("iced-shaken-espresso")!!,
            DrinkRepository.getDrinkById("virgin-classic-mojito")!!,
            DrinkRepository.getDrinkById("oreo-milkshake")!!
        )
    }

    var selectedDrink by remember { mutableStateOf(candidateDrinks.first()) }
    var selectedCupSize by remember { mutableStateOf(CupSize.OZ_16) }
    var espressoShots by remember { mutableIntStateOf(2) }
    var caramelPumps by remember { mutableIntStateOf(2) }
    var vanillaPumps by remember { mutableIntStateOf(0) }
    var condensedMilkPumps by remember { mutableIntStateOf(2) }
    var selectedMilk by remember { mutableStateOf("حليب طازج كامل الدسم / Whole Milk") }
    var selectedIce by remember { mutableStateOf("ثلج قياسي / Regular Ice") }
    var sweetnessLevel by remember { mutableStateOf("100% قياسي / Standard") }

    Scaffold(
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
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("customizer_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "مختبر تعديل الطلبات المخصصة",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Barista Custom Order Lab & Ticket Calculator",
                            fontSize = 12.sp,
                            color = CoffeeCaramel,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        },
        modifier = modifier.testTag("customizer_screen")
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 40.dp)
        ) {
            // Base Drink Selection
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "اختر المشروب الأساسي • Select Base Drink",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            candidateDrinks.forEach { drink ->
                                val isSelected = drink.id == selectedDrink.id
                                Surface(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable { selectedDrink = drink },
                                    shape = RoundedCornerShape(10.dp),
                                    color = if (isSelected) CoffeeCaramel.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                                    border = if (isSelected) CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(CoffeeCaramel)) else null
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "${drink.nameAr} (${drink.nameEn})",
                                            fontSize = 13.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                            color = if (isSelected) CoffeeCaramel else MaterialTheme.colorScheme.onSurface
                                        )
                                        if (isSelected) {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = null,
                                                tint = CoffeeCaramel,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Cup Size (12oz, 16oz, 20oz)
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "حجم الكوب • Cup Size",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            CupSize.entries.forEach { size ->
                                FilterChip(
                                    selected = size == selectedCupSize,
                                    onClick = { selectedCupSize = size },
                                    label = { Text("${size.ounces}oz / ${size.labelAr}") },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }

            // Espresso Shots & Syrups
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "الشوتات والمضخات • Shots & Syrup Pumps",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        // Espresso Shots
                        CounterRow(
                            labelAr = "شوتات الإسبريسو (19جم للشوت)",
                            labelEn = "Espresso Shots (19g each)",
                            count = espressoShots,
                            onIncrement = { if (espressoShots < 4) espressoShots++ },
                            onDecrement = { if (espressoShots > 1) espressoShots-- }
                        )

                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                        // Caramel Pumps
                        CounterRow(
                            labelAr = "مضخات الكراميل (10 مل للمضخة)",
                            labelEn = "Caramel Pumps (10ml each)",
                            count = caramelPumps,
                            onIncrement = { if (caramelPumps < 6) caramelPumps++ },
                            onDecrement = { if (caramelPumps > 0) caramelPumps-- }
                        )

                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                        // Condensed Milk Pumps
                        CounterRow(
                            labelAr = "مضخات حليب مكثف محلى (12جم)",
                            labelEn = "Condensed Milk Pumps (12g each)",
                            count = condensedMilkPumps,
                            onIncrement = { if (condensedMilkPumps < 6) condensedMilkPumps++ },
                            onDecrement = { if (condensedMilkPumps > 0) condensedMilkPumps-- }
                        )
                    }
                }
            }

            // Milk Choice
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "نوع الحليب • Milk Selection",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        val milks = listOf(
                            "حليب طازج كامل الدسم / Whole Milk",
                            "حليب شوفان باريستا / Barista Oat Milk",
                            "حليب لوز غير محلى / Almond Milk",
                            "حليب قليل الدسم / Low Fat Milk"
                        )
                        milks.forEach { milk ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedMilk = milk }
                                    .padding(vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = selectedMilk == milk,
                                    onClick = { selectedMilk = milk }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = milk,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }

            // Real-Time Generated Barista Order Ticket
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = CoffeeLatteCream.copy(alpha = 0.5f)
                    ),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "تذكرة الباريستا المخصصة • BARISTA ORDER TICKET",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace,
                                color = CoffeeDark
                            )
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = CoffeeCaramel
                            ) {
                                Text(
                                    text = "${selectedCupSize.ounces}oz",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "ORDER: ${selectedDrink.nameEn} (مخصص)",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            color = CoffeeEspresso
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        val totalEspressoGrams = espressoShots * 19
                        val totalEspressoYield = espressoShots * 19
                        val totalCaramelMl = caramelPumps * 10
                        val totalCondensedGrams = condensedMilkPumps * 12

                        Text("• Cup Size: ${selectedCupSize.ounces}oz (${selectedCupSize.labelEn})", fontFamily = FontFamily.Monospace, fontSize = 12.sp)
                        Text("• Espresso: $espressoShots Shots ($totalEspressoGrams g dose -> ~$totalEspressoYield g extraction)", fontFamily = FontFamily.Monospace, fontSize = 12.sp)
                        if (caramelPumps > 0) {
                            Text("• Caramel Syrup: $caramelPumps Pumps ($totalCaramelMl ml)", fontFamily = FontFamily.Monospace, fontSize = 12.sp)
                        }
                        if (condensedMilkPumps > 0) {
                            Text("• Sweetened Condensed: $condensedMilkPumps Pumps ($totalCondensedGrams g)", fontFamily = FontFamily.Monospace, fontSize = 12.sp)
                        }
                        Text("• Milk: $selectedMilk", fontFamily = FontFamily.Monospace, fontSize = 12.sp)

                        Spacer(modifier = Modifier.height(14.dp))

                        Button(
                            onClick = { onNavigateToDrinkDetail(selectedDrink.id) },
                            colors = ButtonDefaults.buttonColors(containerColor = CoffeeCaramel),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "عرض الوصفة الأصلية الكاملة • View Master Recipe",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CounterRow(
    labelAr: String,
    labelEn: String,
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = labelAr,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = labelEn,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            FilledTonalIconButton(
                onClick = onDecrement,
                modifier = Modifier.size(34.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Decrement",
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                text = "$count",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 14.dp)
            )
            FilledTonalIconButton(
                onClick = onIncrement,
                modifier = Modifier.size(34.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Increment",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
