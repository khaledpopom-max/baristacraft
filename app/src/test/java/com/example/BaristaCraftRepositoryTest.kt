package com.example

import com.example.data.DrinkRepository
import com.example.model.CupSize
import com.example.model.DrinkCategory
import org.junit.Assert.*
import org.junit.Test

class BaristaCraftRepositoryTest {

    @Test
    fun testAllCategoriesPresent() {
        val allDrinks = DrinkRepository.allDrinks
        assertTrue("Drink repository should not be empty", allDrinks.isNotEmpty())

        DrinkCategory.entries.forEach { cat ->
            val count = DrinkRepository.getDrinksByCategory(cat).size
            assertTrue("Category ${cat.nameEn} should have drinks", count > 0)
        }
    }

    @Test
    fun testDrinkMandatoryRequirements() {
        DrinkRepository.allDrinks.forEach { drink ->
            // Name in Arabic and English
            assertTrue("Drink ${drink.id} must have Arabic name", drink.nameAr.isNotBlank())
            assertTrue("Drink ${drink.id} must have English name", drink.nameEn.isNotBlank())

            // Standard cup size
            assertNotNull("Drink ${drink.id} must have standard cup size", drink.standardSize)

            // Tools & Equipment
            assertTrue("Drink ${drink.id} must have tools", drink.tools.isNotEmpty())
            drink.tools.forEach { tool ->
                assertTrue("Tool must have Arabic name", tool.nameAr.isNotBlank())
                assertTrue("Tool must have English name", tool.nameEn.isNotBlank())
            }

            // Precise Ingredients
            assertTrue("Drink ${drink.id} must have ingredients", drink.ingredients.isNotEmpty())
            drink.ingredients.forEach { ing ->
                assertTrue("Ingredient must have Arabic name", ing.nameAr.isNotBlank())
                assertTrue("Ingredient must have English name", ing.nameEn.isNotBlank())
                assertTrue("Ingredient base amount must be > 0", ing.baseAmount > 0f)
            }

            // Steps
            assertTrue("Drink ${drink.id} must have steps", drink.steps.isNotEmpty())
            drink.steps.forEach { step ->
                assertTrue("Step must have Arabic instruction", step.instructionAr.isNotBlank())
                assertTrue("Step must have English instruction", step.instructionEn.isNotBlank())
            }

            // Barista Tips & Secrets
            assertTrue("Drink ${drink.id} must have barista tips", drink.baristaTips.isNotEmpty())
            drink.baristaTips.forEach { tip ->
                assertTrue("Tip must have Arabic content", tip.tipAr.isNotBlank())
                assertTrue("Tip must have English content", tip.tipEn.isNotBlank())
            }
        }
    }

    @Test
    fun testSearchFunctionality() {
        // Arabic search
        val karakResults = DrinkRepository.searchDrinks("كرك")
        assertTrue("Should find Karak with Arabic search", karakResults.any { it.id == "karak-tea" })

        // English search
        val mojitoResults = DrinkRepository.searchDrinks("mojito")
        assertTrue("Should find Mojitos with English search", mojitoResults.size >= 3)

        // Category filter search
        val icedFilter = DrinkRepository.searchDrinks("", DrinkCategory.ICED)
        assertTrue("Filtered search should return iced drinks", icedFilter.all { it.category == DrinkCategory.ICED })
    }

    @Test
    fun testCustomOrderAnalysis() {
        val analysis = DrinkRepository.analyzeCustomOrder("Iced Spanish Latte Extra Caramel")
        assertNotNull("Should recognize custom order query", analysis)
        assertEquals("iced-spanish-latte", analysis!!.matchedDrink.id)
        assertTrue("Should detect extra caramel modifier", analysis.customModifiersEn.any { it.contains("Caramel", ignoreCase = true) })
    }

    @Test
    fun testCupSizeScaling() {
        val drink = DrinkRepository.getDrinkById("iced-spanish-latte")!!
        val milk = drink.ingredients.first { it.unitEn == "ml" }
        val scaled12 = milk.scaledAmount(CupSize.OZ_12.scaleFactor)
        val scaled16 = milk.scaledAmount(CupSize.OZ_16.scaleFactor)
        val scaled20 = milk.scaledAmount(CupSize.OZ_20.scaleFactor)

        assertTrue("12oz should be less than 16oz", scaled12 < scaled16)
        assertTrue("20oz should be greater than 16oz", scaled20 > scaled16)
    }

    @Test
    fun testGeminiBaristaClientOfflineResponse() = kotlinx.coroutines.runBlocking {
        val dialResponse = com.example.ai.GeminiBaristaClient.sendMessage(emptyList(), "How do I dial in espresso?")
        assertTrue("Response should contain espresso advice", dialResponse.contains("إسبريسو") || dialResponse.contains("Espresso"))
        assertTrue("Response should include tools or recipe structure", dialResponse.contains("الأدوات") || dialResponse.contains("Tools"))

        val pantryResponse = com.example.ai.GeminiBaristaClient.sendMessage(emptyList(), "عندي مكونات: إسبريسو وحليب مكثف وقرفة، ماذا أحضر؟")
        assertTrue("Pantry matcher should suggest recipe", pantryResponse.contains("المشروب") || pantryResponse.contains("Recipe") || pantryResponse.contains("لاتيه"))
    }
}
