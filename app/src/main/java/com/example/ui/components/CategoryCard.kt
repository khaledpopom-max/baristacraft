package com.example.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.DrinkCategory
import com.example.ui.theme.*

@Composable
fun CategoryCard(
    category: DrinkCategory,
    drinkCount: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (catColor, icon) = when (category) {
        DrinkCategory.HOT -> Pair(CatHotDrinks, Icons.Default.LocalCafe)
        DrinkCategory.ICED -> Pair(CatIcedDrinks, Icons.Default.AcUnit)
        DrinkCategory.JUICES -> Pair(CatJuices, Icons.Default.LocalDrink)
        DrinkCategory.SMOOTHIES -> Pair(CatSmoothies, Icons.Default.Icecream)
        DrinkCategory.MOJITOS -> Pair(CatMojitos, Icons.Default.Liquor)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .testTag("category_card_${category.id}"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Bubble
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = CircleShape,
                    color = catColor.copy(alpha = 0.15f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = icon,
                            contentDescription = category.nameEn,
                            tint = catColor,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Titles & Description
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = category.nameAr,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = catColor.copy(alpha = 0.12f)
                    ) {
                        Text(
                            text = "$drinkCount وصفات",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = catColor,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Text(
                    text = category.nameEn,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = catColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${category.descriptionAr} • ${category.descriptionEn}",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    lineHeight = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Open",
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
