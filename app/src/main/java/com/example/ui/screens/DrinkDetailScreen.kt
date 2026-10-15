package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.model.CupSize
import com.example.ui.components.BaristaTimerDialog
import com.example.ui.components.BilingualBadge
import com.example.ui.theme.*

@Composable
fun DrinkDetailScreen(
    drinkId: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val drink = remember(drinkId) { DrinkRepository.getDrinkById(drinkId) }

    if (drink == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("المشروب غير متوفر • Drink not found")
        }
        return
    }

    // Dynamic cup size scaling state (defaults to standard size: 16oz or 12oz)
    var selectedCupSize by remember { mutableStateOf(drink.standardSize) }
    var showTimerDialog by remember { mutableStateOf(false) }
    var timerInitialSeconds by remember { mutableIntStateOf(28) }
    var timerTitleAr by remember { mutableStateOf("مؤقت الاستخلاص") }
    var timerTitleEn by remember { mutableStateOf("Extraction Timer") }
    var completedSteps by remember { mutableStateOf(setOf<Int>()) }

    if (showTimerDialog) {
        BaristaTimerDialog(
            initialSeconds = timerInitialSeconds,
            timerLabelAr = timerTitleAr,
            timerLabelEn = timerTitleEn,
            onDismiss = { showTimerDialog = false }
        )
    }

    val categoryColor = when (drink.category) {
        com.example.model.DrinkCategory.HOT -> CatHotDrinks
        com.example.model.DrinkCategory.ICED -> CatIcedDrinks
        com.example.model.DrinkCategory.JUICES -> CatJuices
        com.example.model.DrinkCategory.SMOOTHIES -> CatSmoothies
        com.example.model.DrinkCategory.MOJITOS -> CatMojitos
    }

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
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = onNavigateBack,
                            modifier = Modifier.testTag("drink_detail_back_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Column {
                            Text(
                                text = "بطاقة الوصفة الاحترافية",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Pro Barista Recipe Card",
                                fontSize = 12.sp,
                                color = CoffeeCaramel,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    // Barista Timer Quick Launch Button
                    FilledTonalButton(
                        onClick = {
                            timerInitialSeconds = 28
                            timerTitleAr = "مؤقت التحضير والاستخلاص"
                            timerTitleEn = "Prep & Extraction Timer"
                            showTimerDialog = true
                        },
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = CoffeeLatteCream.copy(alpha = 0.5f),
                            contentColor = CoffeeEspresso
                        ),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier.testTag("drink_detail_timer_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("مؤقت / Timer", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        },
        modifier = modifier.testTag("drink_detail_screen")
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 40.dp)
        ) {
            // 1. DRINK HEADER (Name & Standard Cup Size)
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    border = CardDefaults.outlinedCardBorder(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        // Category & Standard Size Badges
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = categoryColor.copy(alpha = 0.12f)
                            ) {
                                Text(
                                    text = "${drink.category.nameAr} • ${drink.category.nameEn}",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = categoryColor,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = CoffeeCaramel.copy(alpha = 0.12f)
                            ) {
                                Text(
                                    text = "الحجم القياسي: ${drink.standardSize.ounces}oz (${drink.standardSize.labelAr})",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = CoffeeCaramel,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Drink Name (اسم المشروب)
                        Text(
                            text = drink.nameAr,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.testTag("drink_name_ar")
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = drink.nameEn,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = CoffeeCaramel,
                            modifier = Modifier.testTag("drink_name_en")
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Description
                        Text(
                            text = drink.descriptionAr,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 18.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = drink.descriptionEn,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 16.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Dynamic Cup Size Switcher with ratio scaling
                        Text(
                            text = "تعديل حجم الكوب والنسب بدقة (Cup Size Scaling):",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            CupSize.entries.forEach { size ->
                                val isSelected = size == selectedCupSize
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { selectedCupSize = size },
                                    label = {
                                        Text(
                                            text = "${size.ounces}oz / ${size.labelAr}",
                                            fontSize = 11.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                        )
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("cup_size_toggle_${size.ounces}"),
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = CoffeeCaramel,
                                        selectedLabelColor = Color.White
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // 2. TOOLS & EQUIPMENT (الأدوات والمعدات)
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
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
                                color = CoffeeWarmAmber.copy(alpha = 0.2f),
                                modifier = Modifier.size(34.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.Build,
                                        contentDescription = "Tools",
                                        tint = CoffeeCaramel,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "الأدوات والمعدات المطلوبة",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Tools & Equipment Required",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        drink.tools.forEachIndexed { index, tool ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Surface(
                                    shape = CircleShape,
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    modifier = Modifier.size(20.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = "${index + 1}",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "${tool.nameAr} / ${tool.nameEn}",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    if (tool.noteAr.isNotEmpty()) {
                                        Text(
                                            text = "${tool.noteAr} • ${tool.noteEn}",
                                            fontSize = 11.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                            if (index < drink.tools.size - 1) {
                                HorizontalDivider(
                                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }

            // 3. PRECISE INGREDIENTS (المكونات والمقادير بدقة)
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
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
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Surface(
                                    shape = CircleShape,
                                    color = CoffeeCaramel.copy(alpha = 0.15f),
                                    modifier = Modifier.size(34.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = Icons.Default.Scale,
                                            contentDescription = "Ingredients",
                                            tint = CoffeeCaramel,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "المكونات والمقادير بدقة",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "Precise Ingredients (${selectedCupSize.ounces}oz scale)",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }

                            // Scale Factor Badge
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = CoffeeLatteCream
                            ) {
                                Text(
                                    text = "${selectedCupSize.ounces}oz",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = CoffeeEspresso,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        drink.ingredients.forEachIndexed { index, ingredient ->
                            val scaledAmount = ingredient.scaledAmount(selectedCupSize.scaleFactor)
                            val scaledPumps = ingredient.scaledPumps(selectedCupSize.scaleFactor)

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 7.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = ingredient.nameAr,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = ingredient.nameEn,
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                // Precise Measurement Pill (grams/ml/pumps)
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    border = CardDefaults.outlinedCardBorder()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        val displayAmt = if (scaledAmount == scaledAmount.toInt().toFloat()) {
                                            "${scaledAmount.toInt()}"
                                        } else {
                                            "$scaledAmount"
                                        }

                                        Text(
                                            text = "$displayAmt ${ingredient.unitAr}",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )

                                        if (scaledPumps != null) {
                                            Text(
                                                text = " ($scaledPumps مضخة/pumps)",
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = CoffeeCaramel
                                            )
                                        }
                                    }
                                }
                            }

                            if (index < drink.ingredients.size - 1) {
                                HorizontalDivider(
                                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            }

            // 4. STEP-BY-STEP PREPARATION METHOD (خطوات التحضير بالتفصيل)
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
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
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Surface(
                                    shape = CircleShape,
                                    color = CoffeeCaramel.copy(alpha = 0.15f),
                                    modifier = Modifier.size(34.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = Icons.Default.FormatListNumbered,
                                            contentDescription = "Steps",
                                            tint = CoffeeCaramel,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "خطوات التحضير بالتفصيل",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "Step-by-Step Preparation Method",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }

                            Text(
                                text = "${completedSteps.size}/${drink.steps.size} مكتمل",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (completedSteps.size == drink.steps.size) Color(0xFF2E7D32) else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        drink.steps.forEach { step ->
                            val isCompleted = completedSteps.contains(step.stepNumber)

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .clickable {
                                        completedSteps = if (isCompleted) {
                                            completedSteps - step.stepNumber
                                        } else {
                                            completedSteps + step.stepNumber
                                        }
                                    }
                                    .testTag("step_card_${step.stepNumber}"),
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (isCompleted) Color(0xFFE8F5E9) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                                ),
                                border = CardDefaults.outlinedCardBorder()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Surface(
                                                shape = CircleShape,
                                                color = if (isCompleted) Color(0xFF2E7D32) else CoffeeCaramel,
                                                modifier = Modifier.size(24.dp)
                                            ) {
                                                Box(contentAlignment = Alignment.Center) {
                                                    if (isCompleted) {
                                                        Icon(
                                                            imageVector = Icons.Default.Check,
                                                            contentDescription = "Done",
                                                            tint = Color.White,
                                                            modifier = Modifier.size(16.dp)
                                                        )
                                                    } else {
                                                        Text(
                                                            text = "${step.stepNumber}",
                                                            fontSize = 12.sp,
                                                            fontWeight = FontWeight.Bold,
                                                            color = Color.White
                                                        )
                                                    }
                                                }
                                            }

                                            Spacer(modifier = Modifier.width(10.dp))

                                            Column {
                                                Text(
                                                    text = step.titleAr,
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = MaterialTheme.colorScheme.onSurface
                                                )
                                                Text(
                                                    text = step.titleEn,
                                                    fontSize = 11.sp,
                                                    fontWeight = FontWeight.Medium,
                                                    color = CoffeeCaramel
                                                )
                                            }
                                        }

                                        // Step Timer Launch Button if step has duration
                                        if (step.durationSeconds != null) {
                                            FilledTonalButton(
                                                onClick = {
                                                    timerInitialSeconds = step.durationSeconds
                                                    timerTitleAr = "خطوة ${step.stepNumber}: ${step.titleAr}"
                                                    timerTitleEn = "Step ${step.stepNumber}: ${step.titleEn}"
                                                    showTimerDialog = true
                                                },
                                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                                                modifier = Modifier.height(30.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Timer,
                                                    contentDescription = null,
                                                    modifier = Modifier.size(13.dp)
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text("${step.durationSeconds}s", fontSize = 11.sp)
                                            }
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = step.instructionAr,
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        lineHeight = 17.sp
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = step.instructionEn,
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        lineHeight = 15.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // 5. PRO BARISTA TIPS & CUSTOMIZATIONS (أسرار الباريستا والتعديلات)
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
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
                                color = Color(0xFFFFB300).copy(alpha = 0.2f),
                                modifier = Modifier.size(34.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.Lightbulb,
                                        contentDescription = "Tips",
                                        tint = Color(0xFFF57F17),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "أسرار الباريستا والتعديلات الاحترافية",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Pro Barista Tips & Customization Options",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Pro Secrets List
                        drink.baristaTips.forEach { tip ->
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = CoffeeLatteCream.copy(alpha = 0.35f),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.Star,
                                            contentDescription = null,
                                            tint = Color(0xFFF57F17),
                                            modifier = Modifier.size(15.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "${tip.titleAr} / ${tip.titleEn}",
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = CoffeeDark
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = tip.tipAr,
                                        fontSize = 12.sp,
                                        color = CoffeeDark,
                                        lineHeight = 16.sp
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = tip.tipEn,
                                        fontSize = 11.sp,
                                        color = CoffeeRoast,
                                        lineHeight = 15.sp
                                    )
                                }
                            }
                        }

                        // Customization Options (if drink has special mods)
                        if (drink.customOptions.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "خيارات وتعديلات شائعة من الزبائن (Common Customizations):",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            drink.customOptions.forEach { opt ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "• ${opt.nameAr} (${opt.nameEn})",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = MaterialTheme.colorScheme.surfaceVariant
                                    ) {
                                        Text(
                                            text = opt.amountAr,
                                            fontSize = 11.sp,
                                            color = CoffeeCaramel,
                                            fontWeight = FontWeight.SemiBold,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
