package com.example.ai

import com.example.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

data class ChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val sender: MessageSender,
    val text: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isRecipe: Boolean = false
)

enum class MessageSender {
    USER,
    BARISTA_AI
}

object GeminiBaristaClient {

    private const val MODEL_NAME = "gemini-3.8-flash"
    private const val FALLBACK_MODEL = "gemini-3.5-flash"
    private const val BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private const val SYSTEM_INSTRUCTION = """
You are "Master Barista AI" (باريستا الذكاء الاصطناعي الخبير) — an elite coffee educator, master mixologist, and professional café consultant.
Tone: Professional, encouraging, authoritative, and practical for high-speed café environments.
Language: Fully bilingual. If the user writes in Arabic, answer primarily in fluent, rich Arabic (including common barista terms). If the user writes in English, answer in English. If requested, provide a dual bilingual response.

Your Core Capabilities:
1. Step-by-Step Guidance: Walk the user interactively through preparing any drink (espresso extraction, milk steaming at 60-65°C, cold brew, frappes, mojitos, layering juices by specific gravity).
2. Smart Recipe Customization: Adjust recipes on the fly for custom requests (extra caramel pumps, oat/almond milk swaps, half sweet, extra espresso shots).
3. Pantry Matcher: When the user lists ingredients on hand (e.g. "I have espresso, condensed milk, cinnamon, and ice"), invent and format an exceptional drink recipe.
4. Troubleshooting Assistant: Diagnose preparation mistakes (channeling, bitter/sour shots, watery milk foam, curdled smoothies, bitter muddled mint).

Structured Output Format for Recipes:
Whenever you generate or explain a beverage recipe, YOU MUST ALWAYS format it cleanly using this structure:
🍹 [اسم المشروب / Drink Name] • [Cup Size: 12oz / 16oz / 20oz]
🛠️ الأدوات والمعدات / Tools & Equipment:
• [Tool 1]
• [Tool 2]

⚖️ المكونات والمقادير بدقة / Precise Ingredients:
• [Ingredient 1: exact grams, ml, or pump count]
• [Ingredient 2: exact grams, ml, or pump count]

📋 خطوات التحضير بالتفصيل / Step-by-Step Assembly:
1. [Step 1]
2. [Step 2]
3. [Step 3]

💡 أسرار الباريستا والتزيين / Barista Pro-Tip & Garnish Note:
• [Golden secret, temperature, timing, or garnish note]
"""

    suspend fun sendMessage(
        conversationHistory: List<ChatMessage>,
        userMessage: String
    ): String = withContext(Dispatchers.IO) {
        val rawApiKey = try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Throwable) {
            ""
        }
        val apiKey = rawApiKey.replace("\"", "").trim()

        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY" || apiKey.length < 20) {
            // Intelligent local barista expert fallback if secret key is placeholder or unset
            return@withContext getOfflineBaristaResponse(userMessage)
        }

        try {
            val responseText = callGeminiApi(apiKey, MODEL_NAME, conversationHistory, userMessage)
            return@withContext responseText
        } catch (t: Throwable) {
            return@withContext getOfflineBaristaResponse(userMessage)
        }
    }

    private fun callGeminiApi(
        apiKey: String,
        model: String,
        history: List<ChatMessage>,
        userMessage: String
    ): String {
        val url = "$BASE_URL$model:generateContent?key=$apiKey"

        val contentsJson = JSONArray()

        // Append recent conversation history (last 8 turns for efficiency)
        val recentHistory = history.takeLast(8)
        for (msg in recentHistory) {
            val contentObj = JSONObject()
            contentObj.put("role", if (msg.sender == MessageSender.USER) "user" else "model")
            val partsArray = JSONArray()
            val partObj = JSONObject()
            partObj.put("text", msg.text)
            partsArray.put(partObj)
            contentObj.put("parts", partsArray)
            contentsJson.put(contentObj)
        }

        // Add current user message
        val currentContent = JSONObject()
        currentContent.put("role", "user")
        val currentParts = JSONArray()
        val currentPart = JSONObject()
        currentPart.put("text", userMessage)
        currentParts.put(currentPart)
        currentContent.put("parts", currentParts)
        contentsJson.put(currentContent)

        // System Instruction
        val systemInstructionObj = JSONObject()
        val systemParts = JSONArray()
        val systemPart = JSONObject()
        systemPart.put("text", SYSTEM_INSTRUCTION)
        systemParts.put(systemPart)
        systemInstructionObj.put("parts", systemParts)

        // Generation Config
        val genConfig = JSONObject()
        genConfig.put("temperature", 0.7)
        genConfig.put("topP", 0.95)

        val rootRequest = JSONObject()
        rootRequest.put("contents", contentsJson)
        rootRequest.put("systemInstruction", systemInstructionObj)
        rootRequest.put("generationConfig", genConfig)

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = rootRequest.toString().toRequestBody(mediaType)

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        val response = okHttpClient.newCall(request).execute()
        val responseBody = response.body?.string() ?: throw RuntimeException("Empty response from Gemini")

        if (!response.isSuccessful) {
            throw RuntimeException("Gemini HTTP Error ${response.code}: $responseBody")
        }

        val jsonResponse = JSONObject(responseBody)
        val candidates = jsonResponse.optJSONArray("candidates")
        if (candidates != null && candidates.length() > 0) {
            val firstCandidate = candidates.getJSONObject(0)
            val content = firstCandidate.optJSONObject("content")
            val parts = content?.optJSONArray("parts")
            if (parts != null && parts.length() > 0) {
                return parts.getJSONObject(0).optString("text", "لم يتم توليد إجابة / No answer generated")
            }
        }

        throw RuntimeException("No candidates found in Gemini response")
    }

    private fun getOfflineBaristaResponse(query: String): String {
        val q = query.lowercase()

        return when {
            q.contains("dial") || q.contains("طحن") || q.contains("إسبريسو") || q.contains("espresso") -> {
                """
☕ معايرة الإسبريسو الاحترافية • Dialing In Espresso (1:2 Ratio)

🛠️ الأدوات والمعدات / Tools & Equipment:
• ماكينة إسبريسو تجارية (ضغط 9 بار وحرارة 93°C)
• مطحنة قهوة دقيقة بشفرات مسطحة
• ميزان إسبريسو رقمي بمؤقت (بدقة 0.1 جم)
• موزع WDT ومكبس أفقي مستوٍ

⚖️ المكونات والمقادير بدقة / Precise Measurements:
• بن طازج التحميص (100% أرابيكا): 18.5 جم
• ناتج الاستخلاص في الكوب (Yield): 37 جم
• زمن الاستخلاص المستهدف: 27 إلى 30 ثانية

📋 خطوات الضبط والمعايرة / Dial-in Steps:
1. اطحن 18.5 جم واستخدم إبرة WDT لفك التكتلات وضمان تجانس السرير.
2. اكبس بمستوى أفقي تام بقوة 15 كجم دون أي ميلان.
3. ابدأ الاستخلاص فوراً وراقب المؤقت:
   - إذا انتهى الاستخلاص قبل 24 ثانية: القهوة خفيفة وحامضة (انعم الطحنة Grind Finer).
   - إذا تجاوز الاستخلاص 32 ثانية: القهوة مرة ومحترقة ومقطرة ببطء (اخشن الطحنة Grind Coarser).
   - المثالي: 37 جم في 28 ثانية بكريما بلون البندق وتموجات النمر.

💡 سر الباريستا / Barista Pro-Tip:
اضبط الطحنة فقط عند ثبات كمية البن (Dose = 18.5g). لا تغير متغيرين في نفس الوقت!
"""
            }

            q.contains("foam") || q.contains("رغوة") || q.contains("تبخير") || q.contains("حليب") || q.contains("steam") -> {
                """
🥛 تشخيص وحل رغوة الحليب الخفيفة • Fixing Watery Milk Foam

🛠️ الأدوات المطلوبة:
• بيتشر حليب ستانلس ستيل بحجم 350-500 مل
• عصا تبخير بضغط بخار جاف ونظيف

⚖️ المقادير والحرارة:
• حليب كامل الدسم بارد جداً (4°C من الثلاجة فوراً)
• حرارة التبخير النهائية: 60°C إلى 65°C (140°F - 150°F)

📋 خطوات الحصول على ميكروفوم حريري لامع:
1. تفريغ البخار (Purge): افتح عصا التبخير لثانيتين قبل إدخالها لطرد الماء المتكثف.
2. إدخال الهواء (Stretching): ضع فتحات العصا تحت سطح الحليب بـ 2 ملم فقط لشماع صوت تمزيق الورق (Tch-tch) لأول 3 إلى 5 ثوانٍ لزيادة حجم الحليب 30%.
3. تشكيل الدوامة (Vortex): اغمر العصا 1 سم وميّل البيتشر لتكوين دوامة سريعة تطحن الفقاعات الكبيرة وتحولها لميكروفوم ناعم مثل الدهان السائل.
4. إيقاف البخار عند 60-63°C واطرق البيتشر على الطاولة طرقاً خفيفاً لإزالة أي فقاعة سطحية.

💡 سر الباريستا:
الحليب الدافئ لا يعطيك وقتاً كافياً للتمديد؛ ابدأ دائماً بحليب فائق البرودة (4°C).
"""
            }

            q.contains("مكوناتي") || q.contains("ingredients") || q.contains("suggest") || q.contains("اقترح") || q.contains("عندي") -> {
                """
🍹 ابتكار مشروب من مكوناتك المتوفرة • Pantry Matcher Special

المشروب المقترح: "كولد بريز سبانش لاتيه بالقرفة" (Iced Cinnamon Spanish Breeze)
حجم الكوب: 16 أونصة (16oz / 475ml)

🛠️ الأدوات والمعدات / Tools & Equipment:
• كوب تقديم زجاجي شفاف 16 أونصة
• ملعقة باريستا طويلة للخلط
• مصفاة أو شيكر

⚖️ المكونات والمقادير بدقة / Precise Ingredients:
• إسبريسو دبل شوت طازج: 38 مل (أو 20 جم قهوة مركزة)
• حليب مكثف محلى: 25 جم (أو مضختان)
• حليب طازج بارد: 160 مل
• مكعبات ثلج كريستالية: 150 جم
• قرفة سيلانية مطحونة: رشة ناعمة (1 جم)

📋 خطوات التحضير / Assembly Steps:
1. في قاع الكوب، ضع الحليب المكثف المحلى مع رشة القرفة وحرّك بالملعقة حتى يمتزجا.
2. املأ الكوب بالثلج المجروش أو المكعبات حتى الحافة.
3. اسكب الحليب البارد فوق الثلج ببطء لتتشكل طبقة ناصعة البياض.
4. اسكب شوت الإسبريسو برفق على السطح فوق الثلج لتكوين طبقة عائمة ساحرة.

💡 سر الباريستا / Pro-Tip:
خلط القرفة مع الحليب المكثف في القاع يمنع طفو مسحوق القرفة على السطح دون ذوبان، ويعطي نكهة دافئة مع كل رشفة!
"""
            }

            else -> {
                """
مرحباً بك! أنا "Master Barista AI" — مستشارك وخبير الباريستا الشخصي في BaristaCraft ☕

يمكنني مساعدتك في:
• ضبط طحنة الإسبريسو وتعديل نسبة الاستخلاص (1:2).
• فنون تبخير الحليب ورسم اللاتيه آرت (Microfoam).
• ابتكار مشروبات حسب المكونات المتوفرة لديك حالياً.
• تعديل وتخصيص أي طلب (تعديل السيرب، حليب الشوفان، دبل شوت، تخفيف الحلاوة).
• حل مشاكل المشروبات والموهيتو والسموذي.

جرّب أن تسألني: "كيف أضبط طحنة الإسبريسو؟" أو "عندي إسبريسو وحليب مكثف وقرفة، ماذا أحضر؟"

Welcome! I'm your Master Barista AI. Ask me anything about coffee extraction, drink customization, or ingredient pairing!
"""
            }
        }
    }
}
