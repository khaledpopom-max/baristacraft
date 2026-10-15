package com.example.model

enum class DrinkCategory(
    val id: String,
    val nameAr: String,
    val nameEn: String,
    val descriptionAr: String,
    val descriptionEn: String,
    val iconName: String
) {
    HOT(
        id = "hot",
        nameAr = "المشروبات الساخنة",
        nameEn = "Hot Drinks",
        descriptionAr = "شاي، كرك، سحلب، إسبريسو، لاتيه، كابتشينو، فلات وايت، موكا",
        descriptionEn = "Tea, Karak, Sahlab, Espresso, Americano, Latte, Flat White, Mocha",
        iconName = "Coffee"
    ),
    ICED(
        id = "iced",
        nameAr = "المشروبات المثلجة",
        nameEn = "Iced Drinks",
        descriptionAr = "أمريكانو مثلج، سبانش لاتيه، كولد برو، إسبريسو مخفوق، فرابيه",
        descriptionEn = "Iced Americano, Iced Latte, Cold Brew, Iced Shaken, Frappes",
        iconName = "AcUnit"
    ),
    JUICES(
        id = "juices",
        nameAr = "العصائر الطازجة والكوكتيل",
        nameEn = "Fresh Juices & Cocktails",
        descriptionAr = "مانجو، فراولة، جوافة، برتقال، طبقات إمبراطور وأفوكادو عسل",
        descriptionEn = "Mango, Strawberry, Guava, Fresh Orange, Layered Cocktails",
        iconName = "LocalDrink"
    ),
    SMOOTHIES(
        id = "smoothies",
        nameAr = "السموذي والميلك شيك",
        nameEn = "Smoothies & Milkshakes",
        descriptionAr = "سموذي التوت، ميلك شيك أوريو، لوتس بسكوف، شوكولاتة بلجيكية",
        descriptionEn = "Berry Smoothies, Oreo Shake, Lotus Biscoff, Belgian Chocolate",
        iconName = "Icecream"
    ),
    MOJITOS(
        id = "mojitos",
        nameAr = "الموهيتو",
        nameEn = "Mojitos",
        descriptionAr = "كلاسيك نعناع وليمون، توت أزرق، باشن فروت، بلو أوشن، فراولة",
        descriptionEn = "Virgin Classic Mint-Lime, Blueberry, Passion Fruit, Blue Ocean",
        iconName = "Liquor"
    )
}

enum class CupSize(
    val ounces: Int,
    val labelAr: String,
    val labelEn: String,
    val scaleFactor: Float
) {
    OZ_12(12, "12 أونصة (صغير)", "12 oz (Small)", 0.75f),
    OZ_16(16, "16 أونصة (وسط قياسي)", "16 oz (Standard)", 1.0f),
    OZ_20(20, "20 أونصة (كبير)", "20 oz (Large)", 1.25f)
}

data class ToolItem(
    val nameAr: String,
    val nameEn: String,
    val noteAr: String = "",
    val noteEn: String = ""
)

data class IngredientItem(
    val nameAr: String,
    val nameEn: String,
    val baseAmount: Float, // Amount for 16oz standard
    val unitAr: String,
    val unitEn: String,
    val pumpCount: Int? = null,
    val isScalable: Boolean = true,
    val noteAr: String = "",
    val noteEn: String = ""
) {
    fun scaledAmount(scaleFactor: Float): Float {
        return if (isScalable) {
            val scaled = baseAmount * scaleFactor
            if (scaled >= 10) Math.round(scaled).toFloat() else Math.round(scaled * 10f) / 10f
        } else {
            baseAmount
        }
    }

    fun scaledPumps(scaleFactor: Float): Int? {
        return pumpCount?.let {
            Math.max(1, Math.round(it * scaleFactor))
        }
    }
}

data class PrepStep(
    val stepNumber: Int,
    val titleAr: String,
    val titleEn: String,
    val instructionAr: String,
    val instructionEn: String,
    val durationSeconds: Int? = null,
    val targetTempCelsius: Int? = null
)

data class BaristaTip(
    val titleAr: String,
    val titleEn: String,
    val tipAr: String,
    val tipEn: String,
    val isProSecret: Boolean = true
)

data class CustomOption(
    val nameAr: String,
    val nameEn: String,
    val amountAr: String,
    val amountEn: String
)

data class Drink(
    val id: String,
    val nameAr: String,
    val nameEn: String,
    val category: DrinkCategory,
    val descriptionAr: String,
    val descriptionEn: String,
    val standardSize: CupSize = CupSize.OZ_16,
    val prepTimeMinutes: Int,
    val difficultyAr: String,
    val difficultyEn: String,
    val caloriesKcal: Int,
    val tools: List<ToolItem>,
    val ingredients: List<IngredientItem>,
    val steps: List<PrepStep>,
    val baristaTips: List<BaristaTip>,
    val customOptions: List<CustomOption> = emptyList(),
    val tags: List<String> = emptyList(),
    val isSignature: Boolean = false
)
