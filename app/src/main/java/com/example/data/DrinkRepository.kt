package com.example.data

import com.example.model.*

object DrinkRepository {

    val allDrinks: List<Drink> by lazy {
        listOf(
            // ==========================================
            // 1. HOT DRINKS (المشروبات الساخنة)
            // ==========================================
            Drink(
                id = "karak-tea",
                nameAr = "شاي كرك أصلي بالهيل والزعفران",
                nameEn = "Authentic Karak Chai",
                category = DrinkCategory.HOT,
                descriptionAr = "شاي مركز غني بالتوابل العطرية مع الحليب المبخر والهيل والزعفران.",
                descriptionEn = "Rich spiced black tea simmered with evaporated milk, cardamom, and saffron.",
                standardSize = CupSize.OZ_12,
                prepTimeMinutes = 8,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 160,
                tools = listOf(
                    ToolItem("دلة شاي أو وعاء ستانلس", "Stainless Steel Saucepan / Dallah", "سعة 500 مل", "500ml capacity"),
                    ToolItem("مصفاة شاي ناعمة", "Fine Mesh Strainer", "لتصفية التوابل وأوراق الشاي", "To filter whole spices and tea dust"),
                    ToolItem("ميزان رقمي ومكاييل", "Digital Kitchen Scale", "لدقة قياس الشاي والسكر", "For precise tea and sugar ratio")
                ),
                ingredients = listOf(
                    IngredientItem("ماء نقي مغلي", "Filtered Boiling Water", 250f, "مل", "ml"),
                    IngredientItem("شاي أسود فرط (خرزة/CTC)", "Strong Black Tea Leaves (CTC)", 8f, "جم", "g", isScalable = true),
                    IngredientItem("حليب مبخر ممتاز (مثل بوني أو أبو قوس)", "Evaporated Milk", 150f, "مل", "ml"),
                    IngredientItem("سكر أبيض نقي", "Granulated Cane Sugar", 15f, "جم (أو 1.5 ملعقة)", "g (1.5 tsp)"),
                    IngredientItem("حبوب هيل أخضر مجروشة خفيفاً", "Crushed Green Cardamom Pods", 4f, "حبات", "pods", isScalable = false),
                    IngredientItem("شعيرات زعفران فاخر", "Saffron Threads", 5f, "شعيرات", "threads", isScalable = false),
                    IngredientItem("قرنفل وقرفة (اختياري)", "Cloves & Cinnamon Stick", 1f, "عود صغير", "small piece", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "غلي الماء والشاي", "Boil Water & Tea", "ضع 250 مل ماء في الإناء ودعه يغلي، ثم أضف 8 جم من الشاي الأسود ودعه يغلي لمدة 3 دقائق حتى يصبح لونه داكناً وقوياً.", "Add 250ml water to pot and bring to boil. Add 8g black tea and boil for 3 minutes until dark and robust.", 180),
                    PrepStep(2, "إضافة التوابل والسكر", "Add Spices & Sugar", "أضف الهيل المجروش وشعيرات الزعفران والسكر، واترك المزيج يغلي دقيقة إضافية لتستخلص الزيوت العطرية.", "Crush cardamom pods and add with saffron and sugar. Simmer for 1 minute to release aromatic oils.", 60),
                    PrepStep(3, "إضافة الحليب والتخدير (الفوَران)", "Add Milk & Simmer", "اسكب 150 مل من الحليب المبخر وخفف الحرارة. دع المشروب يرتفع ويهبط 3 مرات (الكركرة) لمدة 4 دقائق ليكتسب قوامه المخملي.", "Pour in 150ml evaporated milk. Lower heat and let it rise and simmer 3 times for 4 minutes to achieve velvet texture.", 240, 92),
                    PrepStep(4, "التصفية والصب من علو", "Strain & Pour from Height", "صفّ الشاي عبر مصفاة دقيقة في الكوب من ارتفاع 20 سم لتكوين رغوة الكرك الذهبية المميزة.", "Strain through fine mesh into cup from 20cm height to generate the signature golden Karak froth.", 30)
                ),
                baristaTips = listOf(
                    BaristaTip("سر الكرك الأصيل", "The Karak Secret", "استخدم شاي CTC الحبيبات لأنه يستحمل الغليان الطويل دون أن تصبح نكهته لاذعة، والحليب المبخر يعطي كثافة لا يمنحها الحليب الطازج العادي.", "Always use CTC granule black tea; it endures prolonged boiling without becoming overly astringent. Evaporated milk provides rich caramelized body that regular milk cannot match."),
                    BaristaTip("ضبط السكر", "Sweetness Customization", "يمكن تقديم الكرك بسكر خفيف (10 جم) أو بدون سكر حسب طلب العميل.", "Default Karak is sweet. Can be dialed down to 10g or zero sugar upon customer preference.")
                ),
                tags = listOf("شاي", "كرك", "karak", "chai", "tea", "زعفران", "هيل", "hot"),
                isSignature = true
            ),

            Drink(
                id = "sahlab",
                nameAr = "سحلب شامي فاخر بالمكسرات والقرفة",
                nameEn = "Royal Middle Eastern Sahlab",
                category = DrinkCategory.HOT,
                descriptionAr = "مشروب شتوي كريمي دافئ محضر بالحليب الطبيعي وبودرة السحلب والمستكة وماء الورد.",
                descriptionEn = "Creamy, velvety winter drink prepared with whole milk, authentic orchid root starch, mastic, and pistachios.",
                standardSize = CupSize.OZ_12,
                prepTimeMinutes = 7,
                difficultyAr = "متوسط",
                difficultyEn = "Medium",
                caloriesKcal = 280,
                tools = listOf(
                    ToolItem("مضرب سلك يدوي ستانلس", "Stainless Steel Whisk", "لمنع أي تكتل أثناء التحريك", "To prevent clumps while heating"),
                    ToolItem("وعاء تسخين حليب سميك القاعدة", "Heavy-Bottom Saucepan", "لتوزيع الحرارة بالتساوي", "For even heat distribution")
                ),
                ingredients = listOf(
                    IngredientItem("حليب بقري كامل الدسم طازج", "Fresh Whole Milk", 300f, "مل", "ml"),
                    IngredientItem("بودرة سحلب صافية أو نشا ذرة فاخر", "Sahlab Starch Powder", 18f, "جم (ملعقة ونصف كبيرة)", "g (1.5 tbsp)"),
                    IngredientItem("سكر أبيض", "Granulated Sugar", 20f, "جم (ملعقتان)", "g (2 tbsp)"),
                    IngredientItem("مستكة مطحونة مع ذرة سكر", "Crushed Ground Mastic", 1f, "جم (فصين)", "g (2 tears)", isScalable = false),
                    IngredientItem("ماء زهر أو ماء ورد نقي", "Orange Blossom / Rose Water", 5f, "مل (ملعقة صغيرة)", "ml (1 tsp)", isScalable = false),
                    IngredientItem("قرفة سيلانية مطحونة", "Ground Ceylon Cinnamon", 2f, "جم للوجه", "g for garnish", isScalable = false),
                    IngredientItem("فستق حلبي وجوز هند محمص", "Crushed Pistachios & Toasted Coconut", 10f, "جم للتقديم", "g for topping", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "تذويب السحلب على البارد", "Cold Starch Dissolve", "في وعاء خارج النار، اخلط 100 مل من الحليب البارد مع بودرة السحلب والسكر بالمضرب حتى يذوب النشا تماماً.", "In a saucepan off the heat, whisk 100ml cold milk with sahlab powder and sugar until completely dissolved.", 60),
                    PrepStep(2, "إضافة باقي الحليب والطهي", "Add Milk & Gentle Simmer", "أضف باقي الحليب (200 مل) وضعه على نار متوسطة هادئة مع التحريك المستمر دون توقف حتى يبدأ المزيج بالتكاثف.", "Add the remaining 200ml milk. Cook over medium-low heat stirring continuously with the whisk until it thickens.", 240, 85),
                    PrepStep(3, "إضافة المستكة وماء الزهر", "Incorporate Mastic & Aroma", "عند وصول القوام لكثافة الكريمة الخفيفة، أضف المستكة المطحونة وماء الزهر، وحرّك لمدة دقيقة واحدة ثم ارفع عن النار.", "When reaching velvety consistency, fold in ground mastic and orange blossom water. Stir 1 min and remove from heat.", 60),
                    PrepStep(4, "التزيين والتقديم ساخناً", "Garnish & Serve", "اسكب السحلب الساخن في الكوب وزين الوجه بخطوط القرفة والفستق الحلبي المقرمش وجوز الهند.", "Pour piping hot into cup. Garnish with a dust of ground cinnamon, bright green crushed pistachios and coconut flakes.", 30)
                ),
                baristaTips = listOf(
                    BaristaTip("سر القوام الحريري المطاطي", "The Velvety Texture", "السر في التحريك المستمر على نار هادئة. لا تترك السحلب يغلي غلياناً عنيفاً حتى لا يحترق في قاع الوعاء أو ينفصل الحليب.", "Continuous gentle whisking prevents bottom scorch and graininess. Keep heat at ~85°C."),
                    BaristaTip("بدائل الحليب", "Dairy-Free Option", "يمكن تحضيره بحليب الشوفان أو حليب اللوز مع نفس مقدار النشا لمحبي النباتي.", "Oat milk pairs wonderfully with cinnamon and pistachios for a plant-based version.")
                ),
                tags = listOf("سحلب", "sahlab", "شتاء", "مكسرات", "حليب", "hot"),
                isSignature = false
            ),

            Drink(
                id = "espresso-double",
                nameAr = "إسبريسو دبل شوت (دوبيو)",
                nameEn = "Espresso Doppio (Double Shot)",
                category = DrinkCategory.HOT,
                descriptionAr = "أساس كل مشروبات القهوة المختصة، استخلاص مركّز بطبقة كريما ذهبية كثيفة.",
                descriptionEn = "The pure soul of specialty coffee. Concentrated extraction with golden hazelnut crema.",
                standardSize = CupSize.OZ_12,
                prepTimeMinutes = 2,
                difficultyAr = "خبير",
                difficultyEn = "Expert",
                caloriesKcal = 5,
                tools = listOf(
                    ToolItem("ماكينة إسبريسو احترافية (9 بار)", "Commercial Espresso Machine (9 Bar)", "ضغط مستقر 9 بار وحرارة 93°C", "Stable 9-bar pressure and 93°C"),
                    ToolItem("مطحنة قهوة دقيقة بشفرات مسطحة", "Burr Grinder with Stepless Adjustment", "طحن إسبريسو ناعم جداً", "Ultra-fine espresso grind"),
                    ToolItem("بورتافلتر دبل + باسكت 18-20 جم", "Bottomless / Double Spout Portafilter", "باسكت دقيق مقاس 58 ملم", "Precision 58mm 18-20g basket"),
                    ToolItem("مكبس باريستا وموزع WDT", "Tamper (15kg pressure) & WDT Needle", "لتوزيع البن وكبسه باستواء تام", "For declumping and level tamping"),
                    ToolItem("ميزان إسبريسو بمؤقت رقمي", "Espresso Timer Scale (0.1g)", "لحساب معدل الاستخلاص بالثواني", "To track yield and extraction ratio")
                ),
                ingredients = listOf(
                    IngredientItem("بن حبوب محمص طازج (100% أرابيكا)", "Fresh Specialty Whole Coffee Beans", 18.5f, "جم (مطحون طازج)", "g (freshly ground)", isScalable = false),
                    IngredientItem("ماء نقي ومفلتر (TDS 100-150)", "Filtered Water for Extraction", 38f, "جم / مل (الناتج في الكوب)", "g / ml yield", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "طحن البن وتوزيعه بالـ WDT", "Grind & Distribute", "اطحن 18.5 جم بن في البورتافلتر. استخدم إبرة WDT لفك أي تكتلات وضمان تجانس السرير كاملاً.", "Dose 18.5g of freshly ground coffee into the portafilter basket. Use WDT needles to eliminate clumps.", 15),
                    PrepStep(2, "الكبس بمستوى أفقي متوازن", "Level Tamp (15kg)", "استخدم الموزع ثم اكبس بقوة 15 كجم مع التأكد من أن سطح البن مستوٍ تماماً 100% دون ميلان.", "Distribute surface and tamp firmly (approx 15kg pressure), verifying the bed is perfectly level.", 10),
                    PrepStep(3, "تطهير رأس الماكينة (Flush)", "Purge Group Head", "قم بعمل Flush لرأس المجموعة لمدة ثانيتين لتنظيف أي بقايا وضبط حرارة الماء على 93°C.", "Flush the group head for 2 seconds to purge old grounds and stabilize brewing temperature to 93°C.", 5),
                    PrepStep(4, "الاستخلاص الدقيق", "Extract to 1:2 Ratio", "ثبت البورتافلتر وشغّل المضخة والمؤقت فوراً. يجب أن يبدأ نزول القهوة في الثانية 6-8 وينتهي عند 36-38 جم خلال 27-30 ثانية.", "Engage portafilter and start timer immediately. First drops at 6-8s; target 37g yield in 27-30 seconds.", 28, 93)
                ),
                baristaTips = listOf(
                    BaristaTip("معادلة الذهب 1:2", "The Golden 1:2 Brew Ratio", "النسبة القياسية للإسبريسو الحديث هي 1:2 (18.5 جم بن = 37 جم استخلاص في الكوب). إذا انتهى الاستخلاص قبل 25 ثانية اضبط الطحنة أنعم، وإذا تجاوز 32 ثانية اجعلها أخشن.", "Standard ratio is 1:2. If extraction finishes under 24s, coffee is under-extracted (grind finer); if past 32s, it tastes bitter/burnt (grind coarser)."),
                    BaristaTip("فحص الكريما", "Crema Evaluation", "الكريما المثالية تكون بلون البندق مع تموجات تايجر (Tiger striping) وتلتئم بسرعة إذا شُقّت بالملعقة.", "High quality crema has hazelnut hue with reddish tiger stripes and self-heals when parted.")
                ),
                tags = listOf("إسبريسو", "قهوة", "espresso", "doppio", "hot", "مختصة"),
                isSignature = false
            ),

            Drink(
                id = "hot-spanish-latte",
                nameAr = "سبانش لاتيه ساخن (Spanish Latte)",
                nameEn = "Hot Spanish Latte",
                category = DrinkCategory.HOT,
                descriptionAr = "مزيج متناغم فائق اللذة يجمع بين قوة الإسبريسو وحلاوة الحليب المكثف مع رغوة مخملية.",
                descriptionEn = "Luxurious balance of rich double espresso, sweet condensed milk, and velvety steamed milk.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 4,
                difficultyAr = "متوسط",
                difficultyEn = "Medium",
                caloriesKcal = 260,
                tools = listOf(
                    ToolItem("ماكينة إسبريسو", "Espresso Machine", "لاستخلاص دبل شوت", "For double shot"),
                    ToolItem("عصا تبخير الحليب", "Commercial Steam Wand", "لصنع ميكروفوم حريري", "To create silky microfoam"),
                    ToolItem("بيتشر حليب ستانلس 500 مل", "Milk Pitcher 500ml", "فوهة حادة لرسم اللاتيه آرت", "Sharp spout for latte art")
                ),
                ingredients = listOf(
                    IngredientItem("إسبريسو دبل شوت طازج", "Fresh Double Espresso Shot", 38f, "جم / مل", "g / ml", isScalable = false),
                    IngredientItem("حليب مكثف محلى فاخر", "Sweetened Condensed Milk", 30f, "جم (أو 2.5 مضخة)", "g (2.5 pumps)", pumpCount = 3),
                    IngredientItem("حليب طازج كامل الدسم", "Fresh Whole Milk", 260f, "مل", "ml"),
                    IngredientItem("حليب مبخر مركز (لمسة باريستا)", "Evaporated Milk (Barista Touch)", 20f, "مل", "ml", isScalable = false),
                    IngredientItem("بودرة قرفة أو كاكاو للسطح", "Cocoa / Cinnamon Dusting", 1f, "رشة اختيارية", "optional dusting", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "وضع الحليب المكثف في قاع الكوب", "Dose Sweetened Milk", "ضع 30 جم من الحليب المكثف المحلى (أو 2.5-3 مضخات) في قاع كوب التقديم سعة 16 أونصة.", "Pump 30g sweetened condensed milk directly into the bottom of a 16oz serving cup.", 10),
                    PrepStep(2, "استخلاص الإسبريسو والدمج", "Pull Espresso & Dissolve", "استخلص دبل شوت إسبريسو ساخن (38 جم) مباشرة فوق الحليب المكثف، وحرّك بالملعقة جيداً حتى يذوب تماماً.", "Extract a double espresso shot directly over the condensed milk. Stir vigorously with a spoon to fully dissolve.", 30),
                    PrepStep(3, "تبخير الحليب بدرجة حرارة 62°C", "Steam Milk to 62°C", "اسكب الحليب في البيتشر وبخّره بإدخال الهواء أول 3 ثوانٍ ثم دوامة مستمرة حتى تصل الحرارة إلى 60-65°C مع رغوة ميكروفوم لامعة.", "Pour milk into pitcher. Stretch milk for 3 seconds then submerge tip to create vortex until 60-65°C microfoam is formed.", 40, 63),
                    PrepStep(4, "السكب والرسم (Latte Art)", "Pour & Latte Art", "اسكب الحليب المتبخر بحركة دائرية لخلط المكونات ثم اقترب من السطح لرسم قلب أو روزيتا أنيقة.", "Pour high to integrate milk and coffee, then drop pitcher tip close to crema surface to draw a tulip or heart.", 20)
                ),
                baristaTips = listOf(
                    BaristaTip("تجنب غليان الحليب فوق 68°C", "Steam Temperature Guard", "عند تبخير الحليب فوق 68°C تتكسر سكريات اللاكتوز الطبيعية ويصبح طعم الحليب مطبوخاً. الدرجة المثالية 60-65°C.", "Never overheat milk past 68°C (155°F); sugars degrade and proteins scald. Target 60-65°C for maximum sweetness and elasticity."),
                    BaristaTip("تعديل الحلاوة", "Sweetness Control", "لطلب قليل الحلاوة (Less Sweet) استعمل 15 جم فقط (مضخة ونصف) من الحليب المكثف.", "For 'Half Sweet / Less Sweet' orders, use 15g (1.5 pumps) condensed milk.")
                ),
                customOptions = listOf(
                    CustomOption("حليب شوفان / Oat Milk", "Oat Milk Swap", "260 مل شوفان باريستا", "260ml Barista Oat Milk"),
                    CustomOption("شوت إضافي / Extra Shot", "Extra Shot", "+ 18 جم إسبريسو", "+18g espresso")
                ),
                tags = listOf("سبانش", "لاتيه", "spanish", "latte", "حليب مكثف", "hot", "قهوة"),
                isSignature = true
            ),

            Drink(
                id = "hot-cappuccino",
                nameAr = "كابتشينو إيطالي كلاسيك",
                nameEn = "Classic Italian Cappuccino",
                category = DrinkCategory.HOT,
                descriptionAr = "التوازن الذهبي: ثلث إسبريسو، ثلث حليب ساخن، وثلث رغوة ميكروفوم حريرية كثيفة.",
                descriptionEn = "The golden ratio: 1/3 espresso, 1/3 steamed milk, 1/3 dense velvet microfoam.",
                standardSize = CupSize.OZ_12,
                prepTimeMinutes = 3,
                difficultyAr = "متوسط",
                difficultyEn = "Medium",
                caloriesKcal = 140,
                tools = listOf(
                    ToolItem("ماكينة إسبريسو وعصا تبخير", "Espresso Machine & Steam Wand", "ضغط بخار عالي وجاف", "High dry steam pressure"),
                    ToolItem("كوب كابتشينو سيراميك دافئ", "Pre-warmed Ceramic Cup 12oz", "حواف سميكة تحفظ الحرارة", "Thick-walled heat retaining cup"),
                    ToolItem("بيتشر حليب 350 مل", "Milk Pitcher 350ml", "ستانلس عالي الجودة", "High grade stainless steel")
                ),
                ingredients = listOf(
                    IngredientItem("إسبريسو دبل شوت مركز", "Double Espresso Shot", 36f, "جم / مل", "g / ml", isScalable = false),
                    IngredientItem("حليب طازج كامل الدسم مبرد جداً", "Cold Fresh Whole Milk (4°C)", 180f, "مل", "ml"),
                    IngredientItem("بودرة كاكاو هولندية فاخرة", "Dutch-Processed Cocoa Powder", 1f, "رشة سطحية اختيارية", "light dusting", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "تحضير الإسبريسو في الكوب المسبق التسخين", "Extract Espresso", "استخلص 36 جم إسبريسو مباشرة في كوب الكابتشينو الدافئ للحفاظ على الكريما وحرارة المشروب.", "Extract 36g double espresso into pre-warmed 12oz cup to preserve crema integrity and heat.", 28, 93),
                    PrepStep(2, "تمديد الحليب وتكوين الرغوة (Aeration)", "Aeration (5-6 seconds)", "ضع فوهة التبخير أسفل سطح الحليب مباشرة وشغّل البخار. اسمح بصوت الشفط (tss-tss) لمدة 5 إلى 6 ثوانٍ لزيادة حجم الحليب بمقدار 40%.", "Submerge steam wand tip just below surface. Introduce air for 5-6 seconds creating gentle paper-tearing sounds.", 15),
                    PrepStep(3, "تكوين الدوامة والتنعيم", "Vortex & Texturing", "اغمر الفوهة قليلاً وميّل البيتشر لتكوين دوامة قوية تكسر الفقاعات وتدمج الرغوة مع الحليب حتى حرارة 62°C.", "Tilt pitcher slightly to form a rolling whirlpool that incorporates foam into glossy microfoam up to 62°C.", 25, 62),
                    PrepStep(4, "الصب واللمسة النهائية", "Pour & Cocoa Dusting", "حرّك البيتشر واطرق قاعه خفيفاً على الطاولة، ثم اسكب في منتصف الكوب لتصعد رغوة بيضاء ناعمة كالحرير.", "Tap pitcher to pop surface bubbles, swirl to shine, and pour steadily into center for a thick foam dome.", 15)
                ),
                baristaTips = listOf(
                    BaristaTip("الفرق بين الكابتشينو واللاتيه", "Cappuccino vs Latte Microfoam", "رغوة الكابتشينو أكثر سماكة (سُمك الرغوة 1.5 إلى 2 سم) بينما اللاتيه رغوته دقيقة جداً ومسطحة (سُمك 0.5 سم).", "Cappuccino features 1.5 - 2cm dense foam cap; Latte has a thinner 0.5cm wet microfoam coat."),
                    BaristaTip("سر الحليب فائق البرودة", "Milk Temperature Starting Point", "استخدم دائماً حليباً مبرداً عند 4°C؛ الحليب البارد يمنحك وقتاً كافياً لإدخال الهواء قبل أن يسخن الحليب.", "Always start with milk right out of the 4°C fridge; gives ample window to introduce microfoam before reaching heat cutoff.")
                ),
                tags = listOf("كابتشينو", "cappuccino", "قهوة", "رغوة", "hot", "إيطالي"),
                isSignature = false
            ),

            Drink(
                id = "hot-flat-white",
                nameAr = "فلات وايت أسترالي (Flat White)",
                nameEn = "Australian Flat White",
                category = DrinkCategory.HOT,
                descriptionAr = "تركيز قهوة أقوى ونكهة واضحة مع رغوة ميكروفوم حريرية رقيقة جداً.",
                descriptionEn = "Bold espresso punch featuring double ristretto and an ultra-thin layer of microfoam.",
                standardSize = CupSize.OZ_12,
                prepTimeMinutes = 3,
                difficultyAr = "متقدم",
                difficultyEn = "Advanced",
                caloriesKcal = 120,
                tools = listOf(
                    ToolItem("ماكينة إسبريسو", "Espresso Machine", "استخلاص ريستريتو قصير", "Ristretto extraction"),
                    ToolItem("كوب فلات وايت سيراميك سعة 180-220 مل", "Flat White Cup (180-220ml)", "حجم مثالي لتركيز النكهة", "Optimal cup size for flavor strength"),
                    ToolItem("بيتشر حليب فوهة مدببة", "Precision Spout Pitcher", "لصنع آرت دقيق", "For crisp latte art")
                ),
                ingredients = listOf(
                    IngredientItem("ريستريتو دبل شوت (1:1.5)", "Double Ristretto Shot", 30f, "جم / مل", "g / ml", isScalable = false),
                    IngredientItem("حليب طازج كامل الدسم متبخر ناعماً", "Steamed Whole Milk (Ultra Microfoam)", 160f, "مل", "ml")
                ),
                steps = listOf(
                    PrepStep(1, "استخلاص دبل ريستريتو", "Pull Double Ristretto", "استخلص 30 جم من القهوة من 19 جم بن خلال 22 ثانية للحصول على استخلاص حلو وكثيف بدون مرارة.", "Extract 30g yield from 19g dose in 22 seconds for sweet, concentrated, low-bitterness ristretto.", 22, 93),
                    PrepStep(2, "تبخير الحليب بطبقة رقيقة", "Steam Ultra-Thin Microfoam", "أدخل الهواء لثانيتين فقط ثم شكّل دوامة مستمرة للحصول على قوام لامع مثل الدهان السائل وبدون فقاعات.", "Aerate for just 2 seconds, then submerge wand into whirlpool to create wet-paint glossy texture without stiff foam.", 30, 60),
                    PrepStep(3, "السكب المخملي", "Flat Pouring", "اسكب الحليب بانسيابية ليمتزج تماماً مع الريستريتو وارسم سطحاً مسطحاً ناعماً (Flat).", "Pour smoothly integrating microfoam through crema, finishing with a flat, glossy surface.", 15)
                ),
                baristaTips = listOf(
                    BaristaTip("ريستريتو وليس إسبريسو عادي", "Ristretto vs Regular Espresso", "الفلات وايت الأصلي يعتمد على الريستريتو (نسبة 1:1.5) لتقليل المرارة وإبراز حلاوة وحمضية البن الممتازة مع الحليب.", "Authentic Flat White uses a tighter ristretto (1:1.5 ratio) bringing forward chocolatey sweetness rather than heavy roast bitterness.")
                ),
                tags = listOf("فلات وايت", "flat white", "قهوة", "ريستريتو", "hot"),
                isSignature = false
            ),

            Drink(
                id = "hot-mocha",
                nameAr = "كافيه موكا بالشوكولاتة البلجيكية",
                nameEn = "Café Mocha Belgian Dark Chocolate",
                category = DrinkCategory.HOT,
                descriptionAr = "المزيج الأيقوني للقهوة والشوكولاتة الداكنة المذابة مع حليب مخملي وكريمة مخفوقة.",
                descriptionEn = "Indulgent harmony of espresso, melted dark Belgian chocolate sauce, velvety milk, and whipped cream.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 4,
                difficultyAr = "متوسط",
                difficultyEn = "Medium",
                caloriesKcal = 320,
                tools = listOf(
                    ToolItem("ماكينة إسبريسو", "Espresso Machine", "استخلاص شوت دبل", "Double shot"),
                    ToolItem("خفاقة صوص صغيرة", "Mini Sauce Whisk", "لإذابة صوص الشوكولاتة في القهوة", "To melt chocolate into espresso"),
                    ToolItem("عصا تبخير وبيتشر", "Steam Wand & Pitcher", "تبخير الحليب", "Milk steaming")
                ),
                ingredients = listOf(
                    IngredientItem("إسبريسو دبل شوت", "Fresh Double Espresso", 38f, "جم / مل", "g / ml", isScalable = false),
                    IngredientItem("صوص شوكولاتة داكنة بلجيكية (أو كاكاو نقي)", "Dark Belgian Chocolate Sauce", 30f, "جم (3 مضخات)", "g (3 pumps)", pumpCount = 3),
                    IngredientItem("حليب طازج كامل الدسم", "Fresh Whole Milk", 240f, "مل", "ml"),
                    IngredientItem("كريمة خفق باريستا (اختياري للوجه)", "Whipped Cream Topping", 30f, "جم", "g", isScalable = false),
                    IngredientItem("برش شوكولاتة داكنة 70%", "Dark Chocolate Shavings", 3f, "جم للتزيين", "g for garnish", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "إذابة صوص الشوكولاتة", "Melt Chocolate in Espresso", "ضع 30 جم صوص شوكولاتة في الكوب، واستخلص الإسبريسو الساخن فوقه مباشرة، ثم اخفق جيداً حتى يذوب الصوص تماماً دون أي ترسب.", "Pump 30g dark chocolate into cup. Pull espresso directly over it, then whisk thoroughly until silky and homogenous.", 30),
                    PrepStep(2, "تبخير الحليب", "Steam Milk", "بخّر 240 مل حليب حتى 63°C بقوام لاتيه حريري ناعم.", "Steam 240ml whole milk to 63°C with smooth latte consistency.", 35, 63),
                    PrepStep(3, "المزج والتزيين", "Integrate & Crown", "اسكب الحليب فوق مزيج القهوة والشوكولاتة مع التقليب، ثم زيّن بقبة من الكريمة المخفوقة وبرش الشوكولاتة.", "Pour steamed milk swirling to integrate with mocha base. Crown with whipped cream swirl and shaved chocolate.", 20)
                ),
                baristaTips = listOf(
                    BaristaTip("صوص حقيقي وليس بودرة سريعة", "Real Chocolate Sauce", "استخدم صوص كاكاو داكن يحتوي على زبدة كاكاو حقيقية؛ البودرات المحلاة الجاهزة تجعل الموكا خفيفة ومائية.", "Use genuine dark chocolate ganache or sauce with real cocoa solids for a rich body without artificial sweetness.")
                ),
                tags = listOf("موكا", "mocha", "شوكولاتة", "chocolate", "hot", "قهوة"),
                isSignature = false
            ),

            Drink(
                id = "hot-americano",
                nameAr = "أمريكانو كلاسيك ساخن",
                nameEn = "Caffè Americano",
                category = DrinkCategory.HOT,
                descriptionAr = "قهوة سوداء نقية وغنية تحافظ على الكريما بالنكهة المتوازنة.",
                descriptionEn = "Pure, aromatic black coffee prepared by pulling double espresso over hot filtered water.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 2,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 5,
                tools = listOf(
                    ToolItem("ماكينة إسبريسو", "Espresso Machine", "استخلاص شوت دبل", "Double shot"),
                    ToolItem("صنبور ماء ساخن مفلتر", "Hot Water Dispenser (90°C)", "ماء بدرجة حرارة محسوبة", "Calibrated 90°C water")
                ),
                ingredients = listOf(
                    IngredientItem("ماء ساخن نقي ومفلتر", "Hot Filtered Water (90°C)", 280f, "مل", "ml"),
                    IngredientItem("إسبريسو دبل شوت طازج", "Fresh Double Espresso Shot", 38f, "جم / مل", "g / ml", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "صب الماء الساخن أولاً", "Water First Method", "صب 280 مل من الماء الساخن (90°C) في الكوب أولاً. (هذه الطريقة تحافظ على طبقة الكريما كاملة).", "Pour 280ml of 90°C hot water into the serving mug first.", 15),
                    PrepStep(2, "استخلاص الإسبريسو على السطح", "Float Espresso on Top", "استخلص دبل شوت الإسبريسو برفق على سطح الماء، لتطفو الكريما الذهبية العطرية فوق المشروب.", "Extract the double espresso shot gently right onto the water surface, creating a gorgeous floating crema ring.", 28, 93)
                ),
                baristaTips = listOf(
                    BaristaTip("الماء أولاً أم القهوة؟", "Water First vs Coffee First", "صب الماء أولاً يحمي الإسبريسو من الاحتراق ويحفظ الكريما الذهبية (Long Black Style). صب الماء فوق القهوة يدمر الكريما.", "Pouring water first preserves the aromatic crema on top. Pouring water on top of espresso breaks down the crema.")
                ),
                tags = listOf("أمريكانو", "americano", "قهوة سوداء", "black coffee", "hot"),
                isSignature = false
            ),

            // ==========================================
            // 2. ICED DRINKS (المشروبات المثلجة)
            // ==========================================
            Drink(
                id = "iced-spanish-latte",
                nameAr = "سبانش لاتيه مثلج مميز (Iced Spanish Latte)",
                nameEn = "Signature Iced Spanish Latte",
                category = DrinkCategory.ICED,
                descriptionAr = "المشروب الأكثر طلباً في الكافيهات! طبقات مبهرة تجمع الحليب المكثف والحليب البارد مع الإسبريسو.",
                descriptionEn = "The top café favorite! Stunning layers of sweet condensed milk, ice-cold milk, and floating bold espresso.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 3,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 290,
                tools = listOf(
                    ToolItem("ماكينة إسبريسو", "Espresso Machine", "استخلاص دبل شوت", "Double shot"),
                    ToolItem("كوب تقديم شفاف سعة 16 أونصة", "Clear Acrylic/Glass 16oz Cup", "لإبراز الطبقات الجمالية", "To display aesthetic layers"),
                    ToolItem("ملعقة باريستا طويلة ومغرفة ثلج", "Long Barista Spoon & Ice Scoop", "للتحريك والطبقات", "For stirring and layering")
                ),
                ingredients = listOf(
                    IngredientItem("حليب مكثف محلى فاخر", "Sweetened Condensed Milk", 35f, "جم (أو 3 مضخات)", "g (3 pumps)", pumpCount = 3),
                    IngredientItem("حليب مبخر مركز", "Evaporated Milk", 20f, "مل (لتعزيز القوام)", "ml (for rich body)", isScalable = false),
                    IngredientItem("مكعبات ثلج كريستالية صلبة", "Solid Crystal Ice Cubes", 160f, "جم (كوب ممتلئ)", "g (full cup)"),
                    IngredientItem("حليب طازج بارد كامل الدسم", "Cold Fresh Whole Milk", 180f, "مل", "ml"),
                    IngredientItem("إسبريسو دبل شوت طازج", "Fresh Double Espresso Shot", 38f, "جم / مل", "g / ml", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "تجهيز قاعدة الحليب المكثف", "Base Layer", "ضع 35 جم من الحليب المكثف و20 مل حليب مبخر في قاع الكوب واخلطهما خفيفاً بالملعقة.", "Pump 35g condensed milk and 20ml evaporated milk into bottom of the 16oz cup and stir.", 15),
                    PrepStep(2, "ملء الكوب بالثلج", "Fill with Ice", "املأ الكوب بمكعبات الثلج الصلبة حتى قرب الحافة (الثلج يعمل كعازل طبيعي يساعد على تدفق الطبقات).", "Fill the cup with solid crystal ice cubes up to 85% of the rim.", 10),
                    PrepStep(3, "سكب الحليب البارد", "Pour Cold Milk", "اسكب 180 مل من الحليب البارد فوق الثلج ببطء لتتشكل الطبقة البيضاء الوسطى.", "Pour 180ml of cold fresh milk gently over the ice, forming the clean white middle band.", 15),
                    PrepStep(4, "صب الإسبريسو على سطح الثلج (Float)", "Float the Espresso", "اسكب دبل شوت الإسبريسو برفق ومباشرة فوق مكعبات الثلج العليا ليبقى عائماً في الأعلى كطبقة بنية داكنة ساحرة.", "Pour the hot double espresso slowly over the top ice cubes to create the distinct floating dark layer.", 20)
                ),
                baristaTips = listOf(
                    BaristaTip("سر طبقة الإسبريسو العائمة", "The Floating Espresso Trick", "اسكب الإسبريسو ببطء فوق مكعب ثلج بارز أو على ظهر ملعقة الباريستا المقلوبة؛ اختلاف الكثافة والحرارة يمنع امتزاجهما قبل التقديم.", "Pour espresso over the back of a bar spoon or onto a large top ice cube to prevent premature sinking."),
                    BaristaTip("تخصيص الكراميل الإضافي (Extra Caramel)", "Extra Caramel Customization", "إذا طلب العميل 'Extra Caramel': أضف مضخة واحدة (10 مل) صوص كراميل مع الحليب المكثف، وخطوط كراميل على جدار الكوب الداخلي قبل وضع الثلج.", "If ordering 'Extra Caramel': add 1 pump (10ml) caramel syrup at the base and drizzle caramel drizzle walls before adding ice.")
                ),
                customOptions = listOf(
                    CustomOption("إكسترا كراميل / Extra Caramel", "Extra Caramel", "+ 1 مضخة كراميل (10 مل) وتزيين الجدار", "+1 pump caramel & cup drizzle"),
                    CustomOption("حليب شوفان / Oat Milk", "Oat Milk Swap", "180 مل حليب شوفان بارد", "180ml Oat Milk"),
                    CustomOption("شوت إضافي / Extra Shot", "Extra Shot", "+ 1 شوت إسبريسو (19 جم)", "+1 espresso shot (19g)")
                ),
                tags = listOf("سبانش", "لاتيه", "مثلج", "iced", "spanish", "caramel", "حليب مكثف", "قهوة"),
                isSignature = true
            ),

            Drink(
                id = "iced-shaken-espresso",
                nameAr = "إسبريسو مخفوق مثلج (Iced Shaken Espresso)",
                nameEn = "Iced Shaken Vanilla Espresso",
                category = DrinkCategory.ICED,
                descriptionAr = "مخفوق بالشيكر مع الثلج والسيرب لتكوين رغوة إسبريسو كريمية باردة مع حليب الشوفان.",
                descriptionEn = "Vigorously shaken double espresso over ice and brown sugar vanilla syrup, topped with splash of oat milk.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 3,
                difficultyAr = "متوسط",
                difficultyEn = "Medium",
                caloriesKcal = 130,
                tools = listOf(
                    ToolItem("شيكر كوكتيل باريستا (بوسطن أو كوبلر)", "Cocktail / Barista Shaker", "لخفق الإسبريسو والثلج بسرعة", "For rapid chilling and aeration"),
                    ToolItem("ماكينة إسبريسو", "Espresso Machine", "استخلاص شوت دبل", "Double shot extraction"),
                    ToolItem("مصفاة شيكر (Hawthorne)", "Strainer / Shaker Lid", "لفصل السائل عن الثلج المسحوق", "To pour liquid and foam")
                ),
                ingredients = listOf(
                    IngredientItem("إسبريسو دبل شوت ساخن طازج", "Fresh Double Espresso", 38f, "جم / مل", "g / ml", isScalable = false),
                    IngredientItem("سيرب سكر بني وفانيليا مدغشقر", "Brown Sugar Vanilla Syrup", 20f, "مل (مضختان)", "ml (2 pumps)", pumpCount = 2),
                    IngredientItem("مكعبات ثلج صلبة للخفق", "Hard Ice Cubes for Shaking", 180f, "جم", "g"),
                    IngredientItem("حليب شوفان باريستا (أو حليب عادي)", "Barista Oat Milk (or Whole Milk)", 80f, "مل (سبلاش نهائي)", "ml (splash on top)")
                ),
                steps = listOf(
                    PrepStep(1, "وضع المكونات في الشيكر", "Add Ingredients to Shaker", "ضع 180 جم ثلج في الشيكر، أضف مضختين من سيرب السكر البني والفانيليا، ثم اسكب شوت الإسبريسو الساخن فوراً.", "Fill shaker with 180g ice, add 2 pumps vanilla syrup, and pour freshly extracted double espresso over ice immediately.", 15),
                    PrepStep(2, "الخفق القوي لمدة 15 ثانية", "Vigorous Shake (15 sec)", "أغلق الشيكر بإحكام واخفق بقوة وبحركة ترددية سريعة لمدة 12-15 ثانية حتى يتحول الإسبريسو لرغوة سميكة مثل الكريمة وتبرد علبة الشيكر تماماً.", "Seal shaker and shake vigorously for 12-15 seconds until dense, creamy espresso foam develops and shaker frosts over.", 15),
                    PrepStep(3, "السكب في كوب التقديم", "Pour Foam & Liquid", "اسكب محتوى الشيكر بالكامل مع الثلج ورغوة الإسبريسو الكثيفة في كوب 16 أونصة.", "Pour entire contents including ice and thick aerated foam into serving cup.", 10),
                    PrepStep(4, "إضافة سبلاش الحليب", "Top with Oat Milk", "اسكب 80 مل من حليب الشوفان البارد فوق الرغوة ليتخللها ببطء وينحدر مشكلاً شلالات رخامية أنيقة.", "Pour 80ml cold oat milk over the top, letting it cascade through the foam in a marbled waterfall.", 10)
                ),
                baristaTips = listOf(
                    BaristaTip("سر الخفق السريع", "Why Shaking Works", "الخفق العنيف يدخل الهواء في زيوت القهوة والكريما لتنتج رغوة حريرية طبيعية شبيهة بالنيترو بدون أي إضافات كيميائية.", "Shaking emulsifies coffee oils with ice and syrup creating micro-bubbles similar to nitro cold brew without special gas.")
                ),
                tags = listOf("شيكن", "مخفوق", "shaken", "espresso", "شوفان", "iced", "فانيليا"),
                isSignature = true
            ),

            Drink(
                id = "cold-brew",
                nameAr = "كولد برو منقوع 18 ساعة (Cold Brew)",
                nameEn = "18-Hour Slow Steeped Cold Brew",
                category = DrinkCategory.ICED,
                descriptionAr = "قهوة باردة منقوعة ببطء لمدة 18 ساعة، منخفضة الحموضة وذات قوام ناعم وطعم شوكولاتي طبيعي.",
                descriptionEn = "Silky slow-steeped coffee steeped for 18 hours in cold filtered water; naturally sweet, low-acid.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 2,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 5,
                tools = listOf(
                    ToolItem("وعاء تخمير كولد برو (Toddy أو Jar)", "Cold Brew Brewing Vessel", "فلتر قماشي أو ورقي ناعم", "With fine cloth/paper filter"),
                    ToolItem("مطحنة بن (طحنة خشنة جداً)", "Coarse Burr Grinder", "مثل حبيبات ملح البحر الخشن", "Coarse sea-salt consistency")
                ),
                ingredients = listOf(
                    IngredientItem("قهوة كولد برو مركزة مصفاة", "Cold Brew Concentrate (1:8 Steep)", 160f, "مل", "ml"),
                    IngredientItem("ماء نقي مثلج أو حليب", "Chilled Filtered Water or Milk", 100f, "مل", "ml"),
                    IngredientItem("مكعبات ثلج كريستالية", "Large Clear Ice Cubes", 150f, "جم", "g"),
                    IngredientItem("شريحة برتقال أو عود قرفة", "Orange Slice / Cinnamon Garnish", 1f, "اختياري", "optional garnish", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "تحضير المنقوع (خطوة مسبقة 18 ساعة)", "18-Hour Steep (Prep)", "انقع 100 جم بن مطحون خشناً في 800 مل ماء بارد في الثلاجة لمدة 18 ساعة، ثم صفّه بفلتر قماش وورق.", "Steep 100g coarse coffee in 800ml cold water in fridge for 18 hours. Filter through cloth and paper.", 60),
                    PrepStep(2, "تجهيز كوب التقديم", "Prep Glass with Ice", "املأ كوب 16 أونصة بمكعبات ثلج كبيرة الحجم.", "Fill 16oz glass with clear ice cubes.", 10),
                    PrepStep(3, "صب القهوة والماء", "Dilute & Serve", "اسكب 160 مل كولد برو مركز مع 100 مل ماء مثلج وحرّك برفق. زيّن بقشرة برتقال معصورة على السطح.", "Pour 160ml concentrate and 100ml cold water over ice. Stir gently and express orange zest on top.", 20)
                ),
                baristaTips = listOf(
                    BaristaTip("تجنب الطحن الناعم", "Never Grind Fine for Cold Brew", "الطحن الناعم يجعل الكولد برو مريراً وموحلاً يصعب تصفيته. الطحن الخشن يبرز نكهات الفواكه المجففة والشوكولاتة.", "Fine grinds over-extract compounds resulting in harsh bitterness and muddy sludge. Stick to coarse French-press grind.")
                ),
                tags = listOf("كولد برو", "cold brew", "قهوة باردة", "منقوع", "iced"),
                isSignature = false
            ),

            Drink(
                id = "iced-americano",
                nameAr = "أمريكانو مثلج (Iced Americano)",
                nameEn = "Iced Caffè Americano",
                category = DrinkCategory.ICED,
                descriptionAr = "انتعاش القهوة الصافية مع الماء المثلج وطبقة الكريما المنعشة في الصيف.",
                descriptionEn = "Crisp, bold espresso poured over ice and cold water for ultimate refreshment.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 2,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 5,
                tools = listOf(
                    ToolItem("ماكينة إسبريسو", "Espresso Machine", "استخلاص شوت دبل", "Double shot"),
                    ToolItem("مغرفة ثلج وكوب 16 أونصة", "Ice Scoop & 16oz Cup", "كوب عالي", "Highball cup")
                ),
                ingredients = listOf(
                    IngredientItem("ماء نقي بارد جداً", "Cold Filtered Water", 200f, "مل", "ml"),
                    IngredientItem("مكعبات ثلج صلبة", "Solid Ice Cubes", 160f, "جم", "g"),
                    IngredientItem("إسبريسو دبل شوت طازج", "Fresh Double Espresso Shot", 38f, "جم / مل", "g / ml", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "ملء الكوب بالثلج والماء", "Ice & Cold Water First", "املأ الكوب بالثلج ثم اسكب 200 مل من الماء البارد فوقه.", "Fill cup with ice then pour 200ml cold water.", 15),
                    PrepStep(2, "سكب الإسبريسو على الوجه", "Pour Espresso over Ice", "اسكب دبل شوت الإسبريسو الساخن مباشرة فوق الثلج ليبرد فوراً وتفوح رائحته الزكية.", "Pour hot double espresso over the ice to flash-chill and release aromatic crema.", 20)
                ),
                baristaTips = listOf(
                    BaristaTip("التبريد الصادم الفوري", "Flash Chilling", "صب الإسبريسو مباشرة فوق الثلج بعد وضع الماء يمنع ذوبان الثلج الزائد ويحافظ على تركيز القهوة المتزن.", "Having water and ice already in place prevents excessive thermal melt, maintaining consistent body.")
                ),
                tags = listOf("أمريكانو", "iced americano", "مثلج", "iced", "قهوة سوداء"),
                isSignature = false
            ),

            Drink(
                id = "coffee-frappe",
                nameAr = "فرابيه القهوة بالكريمة والكراميل",
                nameEn = "Caramel Coffee Frappuccino",
                category = DrinkCategory.ICED,
                descriptionAr = "مشروب مثلج مجروش بقوام كثيف ناعم يجمع بين القهوة المركزة والحليب والكراميل مع الكريمة.",
                descriptionEn = "Blended ice coffee masterpiece with rich espresso, caramel, frappe base, and whipped cream crown.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 4,
                difficultyAr = "متوسط",
                difficultyEn = "Medium",
                caloriesKcal = 340,
                tools = listOf(
                    ToolItem("خلاط عالي السرعة (Commercial Blender)", "Heavy Duty Commercial Blender", "شفرات سحق الثلج", "Ice-crushing blades"),
                    ToolItem("كوب 16 أونصة وغطاء قبة", "16oz Cup with Dome Lid", "للتقديم مع الكريمة", "For whipped cream dome")
                ),
                ingredients = listOf(
                    IngredientItem("إسبريسو دبل شوت مبرد", "Cooled Double Espresso Shot", 40f, "مل", "ml", isScalable = false),
                    IngredientItem("حليب بارد كامل الدسم", "Cold Whole Milk", 120f, "مل", "ml"),
                    IngredientItem("سيرب كراميل ذهبي", "Caramel Syrup", 25f, "مل (2.5 مضخة)", "ml (2.5 pumps)", pumpCount = 3),
                    IngredientItem("بودرة قاعدة فرابيه (مانعة لانفصال الثلج)", "Frappe Base Powder / Xanthan binder", 15f, "جم (ملعقة)", "g (1 scoop)", isScalable = false),
                    IngredientItem("مكعبات ثلج", "Ice Cubes", 180f, "جم", "g"),
                    IngredientItem("كريمة خفق وصوص كراميل للوجه", "Whipped Cream & Caramel Drizzle", 30f, "جم للتزيين", "g topping", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "وضع المكونات في الخلاط", "Add to Blender Jar", "ضع الإسبريسو، الحليب، سيرب الكراميل، بودرة قاعدة الفرابيه، ثم الثلج في الخلاط بالترتيب.", "Add espresso, milk, caramel syrup, frappe base powder, and ice cubes into blender in that order.", 20),
                    PrepStep(2, "الخلط على برنامج الثلج", "Blend on High (25 sec)", "شغّل الخلاط على سرعة منخفضة لكسر الثلج لـ 5 ثوانٍ، ثم سرعة عالية لـ 20 ثانية حتى يصبح القوام كريمياً متجانساً كالحرير.", "Pulse for 5 seconds to crush ice, then blend on high for 20 seconds until thick, creamy, and unified.", 25),
                    PrepStep(3, "الصب والتزيين بالكريمة", "Pour & Garnish", "اسكب في كوب 16 أونصة، زيّن بقبة كريمة مخفوقة وخطوط كراميل ذهبية متقاطعة.", "Pour into 16oz cup, top with generous whipped cream swirl, and criss-cross caramel drizzle.", 20)
                ),
                baristaTips = listOf(
                    BaristaTip("سر عدم انفصال الماء عن الثلج", "Preventing Water Separation", "قاعدة الفرابيه (التي تحتوي على صمغ الزانثان) هي سر قوام الكافيهات التجاري؛ تمنع انفصال الماء عن الثلج ليظل متماسكاً حتى آخر رشفة.", "Frappe base powder contains binders like xanthan gum that keep ice crystals suspended without melting into water.")
                ),
                tags = listOf("فرابيه", "frappe", "frappuccino", "كراميل", "مثلج", "iced", "خلاط"),
                isSignature = false
            ),

            // ==========================================
            // 3. FRESH JUICES & COCKTAILS (العصائر الطازجة والكوكتيل)
            // ==========================================
            Drink(
                id = "layered-emperor-cocktail",
                nameAr = "كوكتيل طبقات إمبراطور الفاخر",
                nameEn = "Royal Emperor Layered Cocktail",
                category = DrinkCategory.JUICES,
                descriptionAr = "تحفة بصرية من طبقات المانجو والجوافة والفراولة الطبيعية الكثيفة مع قطع الفواكه الطازجة والعسل.",
                descriptionEn = "Stunning layered visual masterpiece featuring dense fresh mango, guava, and strawberry purées with fruit cuts & honey.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 6,
                difficultyAr = "متقدم",
                difficultyEn = "Advanced",
                caloriesKcal = 260,
                tools = listOf(
                    ToolItem("خلاط كهربائي عالي القوة", "High Performance Blender", "لخلط العصائر بكثافة عالية جداً", "For ultra-thick fruit purees"),
                    ToolItem("ملعقة باريستا مقلوبة للطبقات", "Layering Bar Spoon", "لتوزيع الطبقات بدون امتزاج", "For slow deflection of layers"),
                    ToolItem("كأس شفاف طويل 16 أونصة", "Tall Clear 16oz Hurricane Glass", "لعرض ألوان الطبقات", "To showcase vibrant colored layers")
                ),
                ingredients = listOf(
                    IngredientItem("بيوريه مانجو سكري/تومي مركز كثيف", "Thick Fresh Mango Puree", 130f, "مل (قاعدة الكوب)", "ml (base layer)"),
                    IngredientItem("بيوريه جوافة فريش مع الحليب وقليل السكر", "Fresh Guava & Milk Puree", 110f, "مل (الطبقة الوسطى)", "ml (middle layer)"),
                    IngredientItem("بيوريه فراولة طازجة بدون ماء", "Pure Fresh Strawberry Puree", 110f, "مل (الطبقة العليا)", "ml (top layer)"),
                    IngredientItem("قطع فواكه مشكلة (كيوي، مانجو، موز)", "Fresh Diced Fruit Chunks", 40f, "جم للوجه", "g on top", isScalable = false),
                    IngredientItem("عسل نحل طبيعي ومكسرات كاجو وفستق", "Pure Blossom Honey & Nuts", 15f, "جم للتزيين", "g garnish", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "خلط بيوريه المانجو الكثيف", "Blend Thick Mango Base", "اخلط لحم المانجو الطازج مع ثلج خفيف وقليل من السكر بدون ماء تماماً لتحصل على قوام أثقل من العسل، واسكبه كأول طبقة في قاع الكوب.", "Blend fresh mango with minimal ice and zero added water into a heavy puree. Pour into glass bottom.", 40),
                    PrepStep(2, "سكب طبقة الجوافة البيضاء", "Layer Guava Milk", "اخلط الجوافة مع قليل من الحليب والثلج ليكون قوامها متوسط الكثافة، واسكبها ببطء شديد على ظهر الملعقة المقلوبة فوق المانجو.", "Blend guava with milk into medium density. Pour very slowly over inverted bar spoon onto the mango.", 40),
                    PrepStep(3, "سكب طبقة الفراولة الحمراء", "Layer Vibrant Strawberry", "اخلط الفراولة الطازجة المجمدة خفيفاً مع سكر قليل واسكبها فوق طبقة الجوافة بنفس طريقة الملعقة.", "Blend strawberries into vibrant red topping layer. Deflect gently over spoon onto white guava.", 40),
                    PrepStep(4, "التزيين بالفواكه والعسل والمكسرات", "Crown with Chunks & Honey", "ضع قطع الفاكهة المشكلة على السطح، وصب خيطاً من عسل النحل ورشة فستق وكاجو محمص.", "Top with diced kiwi, mango, and strawberry chunks. Drizzle raw blossom honey and toasted pistachios.", 30)
                ),
                baristaTips = listOf(
                    BaristaTip("قانون الكثافة والجاذبية في الطبقات", "The Physics of Layering Juices", "سر نجاح الطبقات هو تدرج الكثافة والسكريات: الطبقة السفلية (المانجو) يجب أن تكون الأثقل والأعلى سكر وكثافة، والطبقة الوسطى متوسطة، والعليا هي الأخف وزناً.", "Layering relies on specific gravity and brix density: heaviest/sweetest at bottom (dense mango), intermediate in middle (guava milk), lightest on top (strawberry)."),
                    BaristaTip("استخدام ظهر الملعقة", "The Inverted Spoon Technique", "الملعقة المقلوبة تكسر قوة تدفق العصير وتوزعه أفقياً على السطح بلطف مما يمنع ثقب الطبقة السفلية.", "Rest the spoon tip against inner glass wall and pour liquid down the stem to spread it horizontally.")
                ),
                tags = listOf("كوكتيل", "طبقات", "إمبراطور", "مانجو", "فراولة", "جوافة", "عصير", "juices"),
                isSignature = true
            ),

            Drink(
                id = "fresh-mango-juice",
                nameAr = "عصير مانجو فريش طبيعي مركز",
                nameEn = "Fresh Natural Mango Nectar",
                category = DrinkCategory.JUICES,
                descriptionAr = "عصير مانجو طازج 100% غني بالألياف وقوام مخملي بدون نكهات صناعية مع قطع مانجو.",
                descriptionEn = "100% pure fresh mango nectar with silky texture, natural pulp, and fresh diced mango chunks.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 3,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 190,
                tools = listOf(
                    ToolItem("خلاط باريستا قوي", "Commercial Blender", "لسحق الألياف بنعومة", "To puree mango fiber smoothly"),
                    ToolItem("سكين فاكهة ولوح تقطيع", "Fruit Knife & Cutting Board", "لتقطيع مكعبات المانجو", "For dicing mango cubes")
                ),
                ingredients = listOf(
                    IngredientItem("لحم مانجو طازج ناضج (سكري أو كيت)", "Fresh Ripe Mango Pulp", 280f, "جم", "g"),
                    IngredientItem("ماء بارد مفلتر", "Chilled Filtered Water", 80f, "مل", "ml"),
                    IngredientItem("سكر أبيض نقي (حسب حلاوة الثمرة)", "Fine Granulated Sugar", 15f, "جم (اختياري)", "g (optional)"),
                    IngredientItem("مكعبات ثلج", "Crushed Ice", 100f, "جم", "g"),
                    IngredientItem("مكعبات مانجو طازجة للتقديم", "Diced Fresh Mango Chunks", 40f, "جم في الكوب", "g in cup", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "تقطيع المانجو", "Prep Mango Fruit", "قشر المانجو الناضجة وقطع اللحم مع ترك جزء منها لتقطيعه إلى مكعبات صغيرة للوجه.", "Peel ripe mango and slice pulp, reserving a portion for diced garnish cubes.", 60),
                    PrepStep(2, "الخلط بالثلج والماء", "Blend with Ice", "ضع لحم المانجو والماء البارد والثلج والسكر في الخلاط واخفق لمدة 25 ثانية حتى يصبح القوام ناعماً وكثيفاً.", "Place mango pulp, chilled water, ice, and sugar in blender. Blend for 25 seconds until silky smooth.", 25),
                    PrepStep(3, "التقديم مع قطع المانجو", "Pour & Add Chunks", "اسكب العصير في الكوب وأضف مكعبات المانجو الطازجة على السطح مع شاليموه عريض.", "Pour into 16oz cup, drop fresh mango cubes on top, and serve with a wide boba straw.", 15)
                ),
                baristaTips = listOf(
                    BaristaTip("اختيار صنف المانجو", "Mango Cultivar Choice", "المانجو السكري والفص تعطي حلاوة وعطر لا يضاهى، بينما مانجو تومي وكيت تعطي لوناً برتقالياً وقواماً كثيفاً ممتازاً. المزج بينهما هو سر الكافيهات.", "Blending aromatic sweet Egyptian Sukari with fleshy Keitt mango provides both intoxicating aroma and rich luscious body.")
                ),
                tags = listOf("مانجو", "mango", "عصير طازج", "juices", "طبيعي"),
                isSignature = false
            ),

            Drink(
                id = "fresh-strawberry-juice",
                nameAr = "عصير فراولة بلدي طازج",
                nameEn = "Fresh Farm Strawberry Nectar",
                category = DrinkCategory.JUICES,
                descriptionAr = "عصير فراولة طبيعي أحمر قاني غني بفيتامين C مع نكهة منعشة وقطع فراولة.",
                descriptionEn = "Vibrant crimson fresh strawberry juice packed with natural sweetness and diced berries.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 3,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 140,
                tools = listOf(
                    ToolItem("خلاط فواكه", "Fruit Blender", "للخلط السريع", "For quick blending"),
                    ToolItem("كوب تقديم 16 أونصة", "16oz Glass Cup", "كوب زجاجي", "Glass tumbler")
                ),
                ingredients = listOf(
                    IngredientItem("فراولة طازجة مغسولة ومفرزة خفيفاً", "Fresh Strawberries (semi-frozen)", 260f, "جم", "g"),
                    IngredientItem("ماء بارد مفلتر", "Chilled Filtered Water", 90f, "مل", "ml"),
                    IngredientItem("سكر أبيض نقي", "Granulated Cane Sugar", 20f, "جم", "g"),
                    IngredientItem("عصير ليمون طازج (لحفظ اللون والنكهة)", "Fresh Lemon Juice", 3f, "مل (قطرات)", "ml (few drops)", isScalable = false),
                    IngredientItem("مكعبات ثلج", "Ice Cubes", 80f, "جم", "g")
                ),
                steps = listOf(
                    PrepStep(1, "تجهيز الفراولة", "Stem & Rinse Berries", "اغسل الفراولة جيداً وانزع الأعناق الخضراء واقطعها أنصافاً.", "Rinse berries thoroughly, remove green calyxes, and halve.", 30),
                    PrepStep(2, "الخلط مع قطرات الليمون", "Blend with Lemon Drops", "ضع الفراولة والماء والسكر والثلج وقطرات الليمون في الخلاط، واخفق 20 ثانية.", "Add berries, cold water, sugar, ice, and lemon juice to blender. Blend for 20 seconds.", 20),
                    PrepStep(3, "التقديم", "Serve Fresh", "اسكب في الكوب وزين الحافة بنصف حبة فراولة طازجة.", "Pour into 16oz cup and notch a fresh strawberry on the rim.", 15)
                ),
                baristaTips = listOf(
                    BaristaTip("سر قطرات الليمون", "Why a Drop of Lemon?", "إضافة بضع قطرات من عصير الليمون تبرز حلاوة الفراولة الطبيعية وتمنع أكسدة اللون الأحمر لتحافظ على بريقه الجذاب.", "A squeeze of fresh lemon brightens natural berry acids and prevents oxidation of the vibrant crimson hue.")
                ),
                tags = listOf("فراولة", "strawberry", "عصير", "juices", "طازج"),
                isSignature = false
            ),

            Drink(
                id = "guava-milk-mint",
                nameAr = "عصير جوافة بالحليب والنعناع الفريش",
                nameEn = "Fresh Guava with Milk & Mint",
                category = DrinkCategory.JUICES,
                descriptionAr = "مشروب مصري أصيل كريمي منعش، يجمع نقاء الجوافة مع الحليب البارد وأوراق النعناع العطرية.",
                descriptionEn = "Refreshing creamy Egyptian classic blending fragrant fresh guava with cold milk and garden mint.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 4,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 180,
                tools = listOf(
                    ToolItem("خلاط عصير ومصفاة بذور ناعمة", "Blender & Fine Mesh Strainer", "لتصفية بذور الجوافة الصلبة", "To remove hard seeds"),
                    ToolItem("كوب 16 أونصة", "Serving Cup 16oz", "للتقديم", "For serving")
                ),
                ingredients = listOf(
                    IngredientItem("جوافة بيضاء طازجة ناضجة", "Fresh Ripe White Guavas", 250f, "جم (3 حبات)", "g (3 fruits)"),
                    IngredientItem("حليب بارد كامل الدسم", "Cold Whole Milk", 150f, "مل", "ml"),
                    IngredientItem("سكر أبيض نقي", "Fine Sugar", 22f, "جم", "g"),
                    IngredientItem("أوراق نعناع طازجة", "Fresh Mint Leaves", 4f, "أوراق", "leaves", isScalable = false),
                    IngredientItem("مكعبات ثلج", "Ice Cubes", 80f, "جم", "g")
                ),
                steps = listOf(
                    PrepStep(1, "خلط الجوافة مع الحليب", "Blend Guava & Milk", "ضع الجوافة المقطعة مع الحليب والسكر والثلج في الخلاط واخفق لمدة 15 ثانية على سرعة منخفضة (لتفادي طحن البذور).", "Add chopped guava, milk, sugar, and ice to blender. Blend on low speed for 15s to keep seeds intact.", 15),
                    PrepStep(2, "التصفية السريعة", "Strain Seeds", "صفّ العصير عبر مصفاة شبكية واسعة لعزل البذور الصلبة تماماً.", "Pass through a mesh strainer into a pitcher to separate hard inner seeds.", 30),
                    PrepStep(3, "إضافة النعناع والخلط الخفيف", "Incorporate Mint Aroma", "أعد العصير المصفى إلى الخلاط مع أوراق النعناع واخفق نبضة واحدة (Pulse) ليتداخل عبير النعناع.", "Return strained liquid to blender with fresh mint leaves and pulse once for fragrant infusion.", 10),
                    PrepStep(4, "الصب والتقديم", "Serve Chilled", "اسكب في كوب 16 أونصة مع غصن نعناع طازج على السطح.", "Pour into chilled 16oz glass and garnish with fresh mint sprig.", 10)
                ),
                baristaTips = listOf(
                    BaristaTip("تجنب طحن بذور الجوافة", "Do Not Pulverize Seeds", "استخدم سرعة منخفضة جداً في البداية حتى لا تتكسر بذور الجوافة وتتحول لحبيبات رملية داخل العصير.", "High blender speed grinds seeds into gritty sand. Use gentle low speed just to shear the pulp from seeds.")
                ),
                tags = listOf("جوافة", "guava", "حليب", "نعناع", "عصير", "juices"),
                isSignature = false
            ),

            Drink(
                id = "fresh-orange-juice",
                nameAr = "عصير برتقال فريش معصور على البارد",
                nameEn = "Cold-Pressed Fresh Orange Juice",
                category = DrinkCategory.JUICES,
                descriptionAr = "عصير برتقال طازج 100% معصور فورياً بدون ماء أو سكر مضاف، نقي ومليء بالطاقة.",
                descriptionEn = "100% freshly squeezed Valencia oranges with zero added water or sugar; pure vibrant liquid sunshine.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 3,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 160,
                tools = listOf(
                    ToolItem("عصارة برتقال هيدروليكية أو كهربائية", "Citrus Juicer Machine", "لعصر البرتقال دون لمس القشرة المرة", "To juice without bitter rind oils"),
                    ToolItem("كوب 16 أونصة", "Glass Cup 16oz", "للتقديم الفوري", "For immediate serving")
                ),
                ingredients = listOf(
                    IngredientItem("برتقال عصير طازج (فالنسيا أو بلدي)", "Fresh Juicing Oranges (Valencia)", 450f, "جم (4-5 حبات)", "g (4-5 fruits)"),
                    IngredientItem("مكعبات ثلج (اختياري للتقديم)", "Ice Cubes (optional)", 60f, "جم", "g", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "تقطيع البرتقال", "Halve the Oranges", "اغسل حبات البرتقال الباردة واقطعها إلى أنصاف متساوية.", "Wash chilled oranges and slice into halves.", 20),
                    PrepStep(2, "العصر الفوري", "Squeeze Immediately", "اعصر أنصاف البرتقال في العصارة مباشرة للحصول على 350-380 مل من العصير النقي المفعم بالحيوية.", "Press through citrus juicer yielding 350-380ml pure juice.", 40),
                    PrepStep(3, "التقديم المباشر", "Serve Promptly", "اسكب العصير فوراً في الكوب مع مكعبين ثلج وشريحة برتقال دائرية على الحافة.", "Pour immediately into glass with optional ice and a fresh orange wheel on rim.", 15)
                ),
                baristaTips = listOf(
                    BaristaTip("التقديم الفوري", "Serve Right Away", "عصير البرتقال يفقد فيتامين C ويبدأ طعمه بالتغير بعد 15 دقيقة من العصر بسبب الأكسدة؛ قدمه دائماً طازجاً على الفور.", "Fresh orange juice begins oxidizing within 15 minutes, shifting acidity. Always squeeze on demand.")
                ),
                tags = listOf("برتقال", "orange", "طازج", "عصير", "juices", "فيتامين"),
                isSignature = false
            ),

            // ==========================================
            // 4. SMOOTHIES & MILKSHAKES (السموذي والميلك شيك)
            // ==========================================
            Drink(
                id = "oreo-milkshake",
                nameAr = "ميلك شيك أوريو الكلاسيكي بالآيس كريم",
                nameEn = "Classic Oreo Cookies & Cream Shake",
                category = DrinkCategory.SMOOTHIES,
                descriptionAr = "الميلك شيك المحبوب عالمياً؛ آيس كريم فانيليا غني مع قطع بسكويت أوريو المقرمشة وصوص شوكولاتة.",
                descriptionEn = "Rich vanilla bean ice cream blended with crunchy Oreo cookies, whole milk, and fudge drizzle.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 4,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 420,
                tools = listOf(
                    ToolItem("خلاط ميلك شيك قوي", "High-Torque Milkshake Blender", "لخلط الآيس كريم الكثيف", "To churn heavy ice cream"),
                    ToolItem("مغرفة آيس كريم احترافية", "Ice Cream Scoop", "لعيار كرات الآيس كريم", "To portion ice cream scoops"),
                    ToolItem("كوب 16 أونصة وشاليموه عريض", "16oz Shake Glass & Wide Straw", "للتقديم", "For thick beverage")
                ),
                ingredients = listOf(
                    IngredientItem("آيس كريم فانيليا كريمي فاخر", "Premium Vanilla Ice Cream", 200f, "جم (3 بولات كبيرة)", "g (3 large scoops)"),
                    IngredientItem("حليب بارد كامل الدسم", "Cold Whole Milk", 120f, "مل", "ml"),
                    IngredientItem("بسكويت أوريو الأصلي", "Oreo Sandwich Cookies", 5f, "قطع (4 للخلط + 1 للوجه)", "pieces (4 blend + 1 garnish)", isScalable = false),
                    IngredientItem("صوص شوكولاتة بلجيكية لتزيين الكوب", "Dark Chocolate Fudge Drizzle", 20f, "جم", "g", isScalable = false),
                    IngredientItem("كريمة خفق باريستا مخفوقة", "Whipped Cream Topping", 35f, "جم", "g", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "تزيين جدار الكوب بالصوص", "Drizzle Glass Walls", "قم بعمل خطوط حلزونية من صوص الشوكولاتة على الجدار الداخلي لكوب 16 أونصة وضعه في الثلاجة لدقيقة.", "Drizzle spiral chocolate fudge along inner walls of 16oz cup and chill for 1 minute.", 15),
                    PrepStep(2, "وضع المكونات في الخلاط", "Add to Blender", "ضع 3 بولات آيس كريم فانيليا، الحليب البارد، و4 قطع بسكويت أوريو مكسرة خفيفاً.", "Add 3 scoops vanilla ice cream, 120ml cold milk, and 4 lightly crushed Oreos into blender.", 15),
                    PrepStep(3, "الخلط النبضي (Pulse)", "Pulse Blend (15 sec)", "اخفق لمدة 12-15 ثانية فقط حتى يتجانس الآيس كريم وتبقى قطع أوريو صغيرة ومقرمشة.", "Blend for 12-15 seconds. Do not over-blend so crunchy Oreo cookie bits remain intact.", 15),
                    PrepStep(4, "الصب والتزيين الملكي", "Pour & Crown with Cream", "اسكب المزيج الكثيف في الكوب، وضع قبة كريمة مخفوقة وفتات أوريو مع حبة أوريو كاملة في الأعلى.", "Pour thick shake into cup, crown with whipped cream, Oreo crumbs, and a whole cookie.", 20)
                ),
                baristaTips = listOf(
                    BaristaTip("سر القوام الكثيف الأصلي", "The Secret to Thick Milkshake", "لا تزد كمية الحليب عن 120 مل، واعتمد على برودة الآيس كريم؛ الميلك شيك الحقيقي يجب أن يكون ثقيلاً يشرب بصعوبة خفيفة في الشاليموه.", "The golden ratio is 3 scoops ice cream to 120ml milk max. Over-adding milk turns a shake into flavored milk."),
                    BaristaTip("تجنب سحق الأوريو للغبار", "Keep Cookie Crunch", "الزبائن يعشقون قرمشة الأوريو؛ لذلك اخلط نبضات متقطعة (Pulse) لتظل قطع البسكويت ملموسة.", "Pulse blending ensures satisfying cookie chunks instead of turning gray and powdery.")
                ),
                tags = listOf("أوريو", "oreo", "ميلك شيك", "milkshake", "آيس كريم", "شوكولاتة", "smoothies"),
                isSignature = true
            ),

            Drink(
                id = "lotus-biscoff-shake",
                nameAr = "ميلك شيك لوتس بسكوف بالكراميل",
                nameEn = "Lotus Biscoff Caramel Shake",
                category = DrinkCategory.SMOOTHIES,
                descriptionAr = "مذاق زبدة اللوتس بالكراميل والتوابل البلجيكية مع الآيس كريم وبسكويت بيسكوف المقرمش.",
                descriptionEn = "Decadent caramelized spiced Biscoff cookie spread blended with vanilla ice cream and biscuit crunch.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 4,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 440,
                tools = listOf(
                    ToolItem("خلاط ميلك شيك", "Milkshake Blender", "لخلط زبدة اللوتس", "To melt and incorporate spread"),
                    ToolItem("ملعقة لوتس وكوب 16 أونصة", "Biscoff Spoon & 16oz Cup", "للتقديم", "For serving")
                ),
                ingredients = listOf(
                    IngredientItem("آيس كريم فانيليا غني", "Vanilla Ice Cream", 200f, "جم (3 بولات)", "g (3 scoops)"),
                    IngredientItem("زبدة لوتس بيسكوف أصلية مذابة خفيفاً", "Authentic Lotus Biscoff Spread", 35f, "جم (ملعقتان كبيرتان)", "g (2 tbsp)"),
                    IngredientItem("حليب كامل الدسم بارد", "Cold Whole Milk", 110f, "مل", "ml"),
                    IngredientItem("بسكويت لوتس بيسكوف مكسر", "Crushed Lotus Biscoff Cookies", 3f, "حبات", "biscuits", isScalable = false),
                    IngredientItem("كريمة خفق وزبدة لوتس سائلة للوجه", "Whipped Cream & Melted Biscoff", 30f, "جم للتزيين", "g for garnish", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "تزيين الكوب بزبدة اللوتس", "Rim & Line Cup", "قم بتسخين ملعقة لوتس لـ 10 ثوانٍ وزين حافة وجدران الكوب بخطوط زبدة اللوتس الذهبية.", "Warm 1 tbsp Biscoff spread slightly and drizzle along cup rim and inner walls.", 20),
                    PrepStep(2, "الخلط", "Blend", "ضع الآيس كريم، الحليب، زبدة اللوتس، وحبتين من البسكويت في الخلاط، واخفق لمدة 15 ثانية.", "Combine ice cream, milk, melted spread, and 2 biscuits in blender. Blend for 15 seconds.", 15),
                    PrepStep(3, "التقديم والتزيين", "Pour & Garnish", "اسكب في الكوب، زيّن بالكريمة المخفوقة ورذاذ زبدة اللوتس وحبة بسكويت كاملة فوق الكريمة.", "Pour into cup, top with whipped cream, drizzle melted Biscoff, and anchor a whole Lotus cookie.", 20)
                ),
                baristaTips = listOf(
                    BaristaTip("تليين زبدة اللوتس", "Warm the Spread First", "سخّن زبدة اللوتس 10 ثوانٍ في الميكروويف قبل وضعها بالخلاط لتذوب بسهولة وتتوزع دون أن تتكتل في شفرات الخلاط الباردة.", "Warm Biscoff spread slightly before blending so it incorporates seamlessly rather than clumping around cold blades.")
                ),
                tags = listOf("لوتس", "lotus", "biscoff", "ميلك شيك", "كراميل", "smoothies"),
                isSignature = true
            ),

            Drink(
                id = "mixed-berry-smoothie",
                nameAr = "سموذي التوت المشكل المنعش (Mixed Berry)",
                nameEn = "Wild Mixed Berry Blast Smoothie",
                category = DrinkCategory.SMOOTHIES,
                descriptionAr = "مزيج منعش من التوت الأزرق وتوت العليق والفراولة مع الزبادي اليوناني والعسل الطبيعي.",
                descriptionEn = "Invigorating frozen blend of blueberries, raspberries, strawberries, Greek yogurt, and blossom honey.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 3,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 210,
                tools = listOf(
                    ToolItem("خلاط عالي القوة لسحق الفواكه المجمدة", "High-Power Ice/Frozen Fruit Blender", "شفرات سميكة", "Heavy blades"),
                    ToolItem("كوب تقديم 16 أونصة", "Smoothie Tumbler 16oz", "للتقديم", "For serving")
                ),
                ingredients = listOf(
                    IngredientItem("توت مشكل مجمد (أزرق، أسود، توت عليق)", "Frozen Mixed Berries (Wild)", 180f, "جم", "g"),
                    IngredientItem("فراولة مجمدة", "Frozen Strawberries", 70f, "جم", "g"),
                    IngredientItem("زبادي يوناني طبيعي (لقوام كريمي)", "Plain Greek Yogurt", 60f, "جم (ملعقتان)", "g (2 tbsp)"),
                    IngredientItem("عصير تفاح طبيعي أو حليب لوز", "100% Apple Juice or Almond Milk", 140f, "مل", "ml"),
                    IngredientItem("عسل نحل طبيعي نقي", "Pure Blossom Honey", 20f, "جم (ملعقة ونصف)", "g (1.5 tbsp)")
                ),
                steps = listOf(
                    PrepStep(1, "إضافة السائل أولاً", "Liquid Base First", "اسكب عصير التفاح والزبادي اليوناني والعسل في قاع الخلاط أولاً (يساعد الشفرات على الدوران بسهولة).", "Pour apple juice, Greek yogurt, and honey into bottom of blender pitcher first.", 15),
                    PrepStep(2, "إضافة التوت المجمد", "Add Frozen Berries", "أضف حبات التوت المشكل المجمد والفراولة فوق السائل.", "Layer frozen mixed berries and strawberries on top.", 10),
                    PrepStep(3, "الخلط حتى القوام المخملي", "Blend to Velvet Purple (30 sec)", "اخفق على سرعة عالية لمدة 30-40 ثانية حتى يتحول للون بنفسجي غني بقوام مثلج كريمي.", "Blend on high speed for 30-40 seconds until smooth, thick, and brilliantly violet.", 35),
                    PrepStep(4, "السكب والتزيين", "Pour & Garnish", "اسكب في الكوب وزين السطح بحبات توت طازجة وورقة نعناع خضراء.", "Pour into 16oz cup and top with fresh blueberries and a mint leaf.", 15)
                ),
                baristaTips = listOf(
                    BaristaTip("توت مجمد بدون ثلج إضافي", "Frozen Fruit Replaces Ice", "استخدام الفواكه المجمدة يغنيك عن إضافة الثلج الذي يخفف نكهة العصير ويجعله مائياً؛ ستحصل على نكهة مركزة 100%.", "Frozen fruit acts as ice; avoids watering down the smoothie, yielding vibrant concentrated color and tart flavor.")
                ),
                tags = listOf("سموذي", "توت", "smoothie", "berry", "زبادي", "صحي", "smoothies"),
                isSignature = false
            ),

            Drink(
                id = "belgian-chocolate-shake",
                nameAr = "ميلك شيك الشوكولاتة البلجيكية الغنية",
                nameEn = "Rich Belgian Chocolate Fudge Shake",
                category = DrinkCategory.SMOOTHIES,
                descriptionAr = "عشاق الشوكولاتة! آيس كريم شوكولاتة داكنة مع صوص الجناش البلجيكي وحليب بارد ومارشملو.",
                descriptionEn = "Ultimate chocolate lover dream: dark chocolate ice cream, rich Belgian ganache, cold milk, and chocolate flakes.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 4,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 410,
                tools = listOf(
                    ToolItem("خلاط ميلك شيك", "Milkshake Blender", "لخلط الآيس كريم", "To blend ice cream"),
                    ToolItem("كوب 16 أونصة", "16oz Glass", "للتقديم", "For serving")
                ),
                ingredients = listOf(
                    IngredientItem("آيس كريم شوكولاتة داكنة فاخر", "Rich Dark Chocolate Ice Cream", 200f, "جم (3 بولات)", "g (3 scoops)"),
                    IngredientItem("صوص شوكولاتة بلجيكية داكنة", "Belgian Dark Chocolate Ganache", 35f, "جم", "g"),
                    IngredientItem("حليب بارد كامل الدسم", "Cold Whole Milk", 110f, "مل", "ml"),
                    IngredientItem("برش شوكولاتة وحبيبات شوكولاتة مقرمشة", "Dark Chocolate Curls & Crispies", 15f, "جم للتزيين", "g for topping", isScalable = false),
                    IngredientItem("كريمة خفق باريستا", "Whipped Cream", 35f, "جم", "g", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "تجهيز الكوب", "Line the Glass", "زيّن جدران الكوب بخطوط سميكة من جناش الشوكولاتة.", "Drizzle rich chocolate ganache around glass interior.", 15),
                    PrepStep(2, "الخلط", "Blend Shake", "ضع آيس كريم الشوكولاتة، الحليب، ونصف صوص الشوكولاتة في الخلاط واخفق لمدة 15 ثانية.", "Combine chocolate ice cream, milk, and half the fudge in blender. Blend for 15 seconds.", 15),
                    PrepStep(3, "التقديم واللمسة الأخيرة", "Crown & Serve", "اسكب في الكوب، زيّن بالكريمة المخفوقة وبرش الشوكولاتة الفاخرة وصوص الشوكولاتة.", "Pour into cup, crown with whipped cream, drizzle chocolate sauce, and dust with curls.", 20)
                ),
                baristaTips = listOf(
                    BaristaTip("توازن المرارة والحلاوة", "Bittersweet Balance", "استخدام آيس كريم شوكولاتة 60-70% كاكاو يعطي طعماً راقياً يبتعد عن السكر الزائد التجاري الرخيص.", "Using chocolate ice cream with genuine cocoa mass delivers profound cocoa depth without sickly sweet notes.")
                ),
                tags = listOf("شوكولاتة", "chocolate", "ميلك شيك", "shake", "بلجيكي", "smoothies"),
                isSignature = false
            ),

            Drink(
                id = "mango-passion-smoothie",
                nameAr = "سموذي مانجو وباشن فروت استوائي",
                nameEn = "Tropical Mango Passion Fruit Smoothie",
                category = DrinkCategory.SMOOTHIES,
                descriptionAr = "انفجار استوائي يجمع حلاوة المانجو مع حموضة الباشن فروت المنعشة والزبادي.",
                descriptionEn = "Tropical sensation blending golden sweet mango with tangy passion fruit seeds and creamy yogurt.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 3,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 220,
                tools = listOf(
                    ToolItem("خلاط سموذي قوي", "Smoothie Blender", "لخلط الفواكه المجمدة", "For frozen fruits"),
                    ToolItem("كوب 16 أونصة", "16oz Cup", "للتقديم", "For serving")
                ),
                ingredients = listOf(
                    IngredientItem("مانجو مجمدة قطع", "Frozen Mango Chunks", 180f, "جم", "g"),
                    IngredientItem("بيوريه باشن فروت مع البذور", "Passion Fruit Puree with Seeds", 50f, "مل", "ml"),
                    IngredientItem("عصير برتقال طبيعي", "Fresh Orange Juice", 120f, "مل", "ml"),
                    IngredientItem("زبادي طبيعي أو حليب جوز هند", "Greek Yogurt or Coconut Milk", 50f, "جم / مل", "g / ml"),
                    IngredientItem("عسل نحل", "Blossom Honey", 15f, "جم", "g")
                ),
                steps = listOf(
                    PrepStep(1, "وضع المكونات في الخلاط", "Add to Blender", "ضع عصير البرتقال والزبادي والعسل وبيوريه الباشن فروت ثم قطع المانجو المجمدة.", "Add orange juice, yogurt, honey, passion fruit, and frozen mango into blender.", 15),
                    PrepStep(2, "الخلط السلس", "Blend Smooth (25 sec)", "اخفق على سرعة عالية حتى يصبح القوام كريمياً أصفر ذهبياً مشرقاً.", "Blend on high speed until sunny golden-yellow and silky smooth.", 25),
                    PrepStep(3, "التقديم", "Pour & Garnish", "اسكب في الكوب وزين السطح بملعقة من بذور الباشن فروت المقرمشة.", "Pour into 16oz cup and spoon fresh crunchy passion fruit seeds on top.", 15)
                ),
                baristaTips = listOf(
                    BaristaTip("توازن النكهة الاستوائية", "Sweet-Sour Harmony", "حموضة الباشن فروت تكسر حلاوة المانجو الثقيلة وتعطي انتعاشاً صيفياً استثنائياً.", "Tangy passion fruit acid cuts through mango sweetness for crisp, tropical refreshment.")
                ),
                tags = listOf("مانجو", "باشن فروت", "سموذي", "smoothie", "tropical", "smoothies"),
                isSignature = false
            ),

            // ==========================================
            // 5. MOJITOS (الموهيتو)
            // ==========================================
            Drink(
                id = "virgin-classic-mojito",
                nameAr = "موهيتو ليمون ونعناع كلاسيك (Virgin Classic)",
                nameEn = "Virgin Classic Mint-Lime Mojito",
                category = DrinkCategory.MOJITOS,
                descriptionAr = "الموهيتو الكلاسيكي المنعش؛ ليمون أخضر طازج ونعناع بلدي مهروس خفيفاً مع مياه غازية وثلج مجروش.",
                descriptionEn = "The timeless classic virgin cooler: muddled fresh lime wedges, bruised mint leaves, sparkling soda & crushed ice.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 3,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 95,
                tools = listOf(
                    ToolItem("مدقة كوكتيل باريستا (Muddler)", "Bar Muddler", "لهرس الليمون والنعناع برفق دون تقطيع الأوراق", "To release oils without tearing mint"),
                    ToolItem("ملعقة بار حلزونية طويلة", "Spiral Bar Spoon", "لتقليب الثلج والمياه الغازية بلطف", "For gentle stirring"),
                    ToolItem("كسارة ثلج / مغرفة ثلج مجروش", "Crushed Ice Scoop", "ثلج مجروش ناعم", "Fine crushed ice scoop"),
                    ToolItem("كوب زجاجي طويل 16 أونصة", "16oz Highball Glass", "للتقديم الفوري", "Highball glass")
                ),
                ingredients = listOf(
                    IngredientItem("ليمون أخضر (لايم) طازج مقطع أرباع", "Fresh Green Lime Wedges", 4f, "قطع (نصف ليمونة)", "wedges (half lime)", isScalable = false),
                    IngredientItem("أوراق نعناع بلدي طازج طري", "Fresh Tender Mint Leaves", 10f, "أوراق كاملة", "leaves", isScalable = false),
                    IngredientItem("سيرب سكر قصب نقي (Simple Syrup) أو سيرب موهيتو", "Pure Cane Sugar Syrup", 25f, "مل (2.5 مضخة)", "ml (2.5 pumps)", pumpCount = 3),
                    IngredientItem("ثلج مجروش ناصع البياض", "Fresh Crushed Ice", 180f, "جم (حتى قمة الكوب)", "g (full cup)"),
                    IngredientItem("مياه صودا غازية فوارة مبردة جداً (Club Soda / Sprite)", "Ice-Cold Sparkling Soda / Sprite", 180f, "مل", "ml"),
                    IngredientItem("شريحة ليمون لايم وغصن نعناع علوي", "Lime Wheel & Mint Crown", 1f, "للتزيين", "for garnish", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "هرس الليمون والسيرب (Muddling)", "Muddle Lime & Syrup", "ضع قطع الليمون الأخضر و25 مل سيرب السكر في قاع الكوب. اضغط بالمدقة 4-5 مرات لعصر العصير واستخلاص الزيوت العطرية من القشرة.", "Place lime wedges and 25ml simple syrup into bottom of glass. Press firmly 4-5 times with muddler to release oils.", 20),
                    PrepStep(2, "تنشيط النعناع (Clapping the Mint)", "Activate the Mint", "صفّق أوراق النعناع بين راحتَي يدك مرة واحدة لتنشيط الزيوت الطيارة، ثم أضفها في الكوب واضغط عليها برفق شديد (لا تهرسها حتى لا تمرر).", "Clap mint leaves between palms once to wake up aroma oils. Drop into glass and press very gently once.", 10),
                    PrepStep(3, "ملء الكوب بالثلج المجروش", "Pack with Crushed Ice", "املأ ثلاثة أرباع الكوب بالثلج المجروش الناعم واخلط بالملعقة الحلزونية من الأسفل إلى الأعلى لتوزيع الليمون والنعناع.", "Fill 3/4 with crushed ice. Stir with spiral spoon bringing limes and mint up into the ice matrix.", 15),
                    PrepStep(4, "إضافة الصودا والثلج الإضافي", "Top with Soda & Crown", "اسكب 180 مل من مياه الصودا الفوارة ببطء، أضف قبة ثلج مجروش على الوجه، وزيّن بغصن نعناع وشريحة ليمون.", "Top with chilled sparkling soda, pack a small crushed ice dome, and crown with fresh mint bouquet and lime wheel.", 15)
                ),
                baristaTips = listOf(
                    BaristaTip("سر تصفيق النعناع (Clap the Mint)", "Never Shred the Mint Leaves", "لا تفرم أو تهرس أوراق النعناع بشدة؛ هرس النعناع يمزق الألياف ويطلق مادة الكلوروفيل المرة. الضغط الخفيف أو التصفيق يستخرج العطر النقي فقط.", "Never over-muddle or shred mint; bruised torn leaves release bitter chlorophyll. A gentle press extracts pure refreshing menthol oils."),
                    BaristaTip("ثلج مجروش وليس مكعبات كبيرة", "Always Use Crushed Ice", "الموهيتو يحتاج ثلجاً مجروشاً ليبرد المشروب فورياً ويمنع المكونات من الاستقرار في القاع.", "Crushed ice flash-chills the soda and suspends mint leaves evenly throughout the drink.")
                ),
                tags = listOf("موهيتو", "mojito", "ليمون", "نعناع", "صودا", "كلاسيك", "منعش", "mojitos"),
                isSignature = true
            ),

            Drink(
                id = "blueberry-mojito",
                nameAr = "موهيتو التوت الأزرق المنعش (Blueberry Mojito)",
                nameEn = "Sparkling Blueberry Mint Mojito",
                category = DrinkCategory.MOJITOS,
                descriptionAr = "حبات التوت الأزرق الطازج المهروسة مع الليمون والنعناع وسيرب البلوبيري والمياه الفوارة.",
                descriptionEn = "Muddled plump blueberries with fresh lime, crushed mint, blueberry syrup, and sparkling soda.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 3,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 120,
                tools = listOf(
                    ToolItem("مدقة كوكتيل باريستا", "Cocktail Muddler", "لهرس حبات التوت والليمون", "To crush berries and lime"),
                    ToolItem("ملعقة بار حلزونية", "Bar Spoon", "للتقليب", "For stirring"),
                    ToolItem("كوب 16 أونصة شفاف", "16oz Clear Glass", "للتقديم", "For serving")
                ),
                ingredients = listOf(
                    IngredientItem("حبات توت أزرق طازجة", "Fresh Plump Blueberries", 35f, "جم (8-10 حبات)", "g (8-10 berries)"),
                    IngredientItem("ليمون أخضر (لايم) مقطع", "Fresh Lime Wedges", 3f, "قطع", "wedges", isScalable = false),
                    IngredientItem("أوراق نعناع طازج", "Fresh Mint Leaves", 8f, "أوراق", "leaves", isScalable = false),
                    IngredientItem("سيرب توت أزرق طبيعي ممتاز", "Premium Blueberry Syrup", 25f, "مل (2.5 مضخة)", "ml (2.5 pumps)", pumpCount = 3),
                    IngredientItem("ثلج مجروش", "Crushed Ice", 180f, "جم", "g"),
                    IngredientItem("مياه صودا غازية أو سفن أب مبرد", "Chilled Sparkling Soda / 7Up", 170f, "مل", "ml"),
                    IngredientItem("حبات توت وغصن نعناع للوجه", "Whole Blueberries & Mint Sprig", 5f, "جم للتزيين", "g garnish", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "هرس التوت والليمون", "Muddle Blueberries & Lime", "ضع حبات التوت الأزرق وقطع الليمون وسيرب التوت في الكوب، واهرس بالمدقة حتى تفرز حبات التوت لونها الأرجواني وعصيرها الغني.", "Place blueberries, lime wedges, and blueberry syrup in glass. Muddle until berries burst and release rich purple juice.", 20),
                    PrepStep(2, "إضافة النعناع المنشط", "Add Bruised Mint", "أضف أوراق النعناع المصفقة واضغط مرة واحدة برفق.", "Add slapped mint leaves and press gently once.", 10),
                    PrepStep(3, "الثلج والتحريك", "Ice & Churn", "املأ الكوب بالثلج المجروش وحرّك بالملعقة من القاع لتوزيع اللون التوتي الزاهي والقطع.", "Fill with crushed ice and churn with bar spoon bringing purple juices up into the ice.", 15),
                    PrepStep(4, "إضافة الصودا والتزيين", "Top with Soda", "اسكب الصودا الباردة حتى الحافة وزين بحبات توت تطفو على الوجه وغصن نعناع.", "Top with effervescent soda, float fresh blueberries on top, and add a lush mint sprig.", 15)
                ),
                baristaTips = listOf(
                    BaristaTip("تأثير اللون الأرجواني", "Vibrant Purple Hue", "هرس التوت الطازج مع السيرب يعطي لوناً بنفسجياً طبيعياً ساحراً يتدرج مع تصاعد فقاعات الصودا.", "Crushing fresh berries extracts anthocyanins creating natural jewel-toned purple gradient with effervescent bubbles.")
                ),
                tags = listOf("توت أزرق", "blueberry", "موهيتو", "mojito", "صودا", "منعش", "mojitos"),
                isSignature = true
            ),

            Drink(
                id = "passion-fruit-mojito",
                nameAr = "موهيتو باشن فروت استوائي (Passion Fruit)",
                nameEn = "Tropical Passion Fruit Mojito",
                category = DrinkCategory.MOJITOS,
                descriptionAr = "مزيج استوائي مثير؛ لب الباشن فروت الطبيعي مع الليمون والنعناع والصودا الفوارة المنعشة.",
                descriptionEn = "Exotic tropical cooler combining real passion fruit pulp & seeds, muddled lime, mint, and crisp soda.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 3,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 110,
                tools = listOf(
                    ToolItem("مدقة كوكتيل", "Muddler", "لهرس الليمون والنعناع", "Muddle citrus"),
                    ToolItem("ملعقة بار حلزونية", "Bar Spoon", "للتقليب", "For stirring"),
                    ToolItem("كوب 16 أونصة", "16oz Glass", "للتقديم", "For serving")
                ),
                ingredients = listOf(
                    IngredientItem("بيوريه باشن فروت طبيعي مع البذور", "Passion Fruit Puree with Seeds", 35f, "مل", "ml"),
                    IngredientItem("قطع ليمون لايم أخضر", "Fresh Lime Wedges", 3f, "قطع", "wedges", isScalable = false),
                    IngredientItem("أوراق نعناع طازج", "Fresh Mint Leaves", 8f, "أوراق", "leaves", isScalable = false),
                    IngredientItem("سيرب سكر قصب", "Simple Cane Syrup", 15f, "مل (1.5 مضخة)", "ml (1.5 pumps)", pumpCount = 2),
                    IngredientItem("ثلج مجروش", "Crushed Ice", 180f, "جم", "g"),
                    IngredientItem("مياه صودا فوارة مثلجة", "Chilled Sparkling Soda Water", 180f, "مل", "ml")
                ),
                steps = listOf(
                    PrepStep(1, "هرس الليمون والسيرب والباشن", "Muddle Base", "ضع قطع الليمون، سيرب السكر، وبيوريه الباشن فروت في الكوب، واهرس الليمون لاستخراج عصيره.", "Add lime wedges, simple syrup, and passion fruit puree into glass. Muddle lime to express juice.", 20),
                    PrepStep(2, "إضافة النعناع والثلج", "Mint & Crushed Ice", "أضف أوراق النعناع ثم املأ الكوب بالثلج المجروش بالكامل.", "Add fresh mint leaves then pack glass full with crisp crushed ice.", 15),
                    PrepStep(3, "التقليب والصب الفوار", "Churn & Top with Soda", "اسكب الصودا الغازية وحرّك بالملعقة لتوزيع بذور الباشن فروت الذهبية المقرمشة في كل الكوب.", "Pour sparkling soda and churn with bar spoon distributing golden crunchy passion seeds evenly.", 20)
                ),
                baristaTips = listOf(
                    BaristaTip("القرمشة الاستوائية للبذور", "Crunchy Seeds Experience", "بذور الباشن فروت آمنة تماماً للأكل وتمنح قرمشة ممتعة وحموضة توازن حلاوة السيرب.", "Passion fruit seeds are edible and provide an authentic tropical crunch and burst of acidity.")
                ),
                tags = listOf("باشن فروت", "passion fruit", "موهيتو", "mojito", "صودا", "استوائي", "mojitos"),
                isSignature = true
            ),

            Drink(
                id = "strawberry-mojito",
                nameAr = "موهيتو الفراولة والنعناع المنعش",
                nameEn = "Fresh Strawberry Mint Mojito",
                category = DrinkCategory.MOJITOS,
                descriptionAr = "فراولة طازجة مهروسة مع شرائح الليمون والنعناع الأخضر وسيرب الفراولة والصودا.",
                descriptionEn = "Muddled ripe sweet strawberries with lime wedges, garden mint, strawberry syrup, and fizzy soda.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 3,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 115,
                tools = listOf(
                    ToolItem("مدقة كوكتيل", "Muddler", "لهرس الفراولة", "Muddle strawberries"),
                    ToolItem("ملعقة بار حلزونية", "Bar Spoon", "للتقليب", "For stirring"),
                    ToolItem("كوب 16 أونصة", "16oz Highball Glass", "للتقديم", "For serving")
                ),
                ingredients = listOf(
                    IngredientItem("فراولة طازجة مقطعة شرائح", "Fresh Sliced Strawberries", 40f, "جم (3 حبات)", "g (3 berries)"),
                    IngredientItem("قطع ليمون أخضر", "Lime Wedges", 3f, "قطع", "wedges", isScalable = false),
                    IngredientItem("أوراق نعناع بلدي", "Fresh Mint Leaves", 8f, "أوراق", "leaves", isScalable = false),
                    IngredientItem("سيرب فراولة نقي", "Strawberry Syrup", 25f, "مل (2.5 مضخة)", "ml (2.5 pumps)", pumpCount = 3),
                    IngredientItem("ثلج مجروش", "Crushed Ice", 180f, "جم", "g"),
                    IngredientItem("مياه صودا فوارة أو سفن أب", "Sparkling Soda / 7Up", 170f, "مل", "ml")
                ),
                steps = listOf(
                    PrepStep(1, "هرس الفراولة والليمون", "Muddle Strawberries & Lime", "اهرس الفراولة وقطع الليمون مع سيرب الفراولة في قاع الكوب حتى يمتزجا.", "Muddle sliced strawberries, lime wedges, and strawberry syrup in the glass base.", 20),
                    PrepStep(2, "إضافة النعناع والثلج", "Mint & Ice", "أضف النعناع واملأ الكوب بالثلج المجروش وحرّك برفق.", "Add slapped mint leaves, pack with crushed ice, and stir gently.", 15),
                    PrepStep(3, "إضافة الصودا والتقديم", "Top with Fizzy Soda", "اسكب الصودا الغازية حتى الحافة وزيّن بشريحة فراولة وغصن نعناع.", "Pour bubbly soda to the rim and garnish with fresh strawberry slice and mint bouquet.", 15)
                ),
                baristaTips = listOf(
                    BaristaTip("حلاوة طبيعية", "Natural Sweetness", "الفراولة الناضجة تقلل حاجتك للسكر الإضافي؛ يمكنك استخدام مياه غازية خالية من السكر للحفاظ على خفة المشروب.", "Naturally ripe berries provide rich fructose allowing zero-calorie soda water pairing.")
                ),
                tags = listOf("فراولة", "strawberry", "موهيتو", "mojito", "صودا", "mojitos"),
                isSignature = false
            ),

            Drink(
                id = "blue-ocean-mojito",
                nameAr = "موهيتو المحيط الأزرق (Blue Ocean / Lagoon)",
                nameEn = "Blue Ocean Lagoon Mojito",
                category = DrinkCategory.MOJITOS,
                descriptionAr = "الموهيتو الأزرق الكهربائي الخلاب بنكهة البلو كوراكاو الحمضية مع الليمون والنعناع والمياه الغازية.",
                descriptionEn = "Mesmerizing electric blue cooler infused with citrusy Blue Curaçao, muddled limes, fresh mint & soda.",
                standardSize = CupSize.OZ_16,
                prepTimeMinutes = 3,
                difficultyAr = "سهل",
                difficultyEn = "Easy",
                caloriesKcal = 105,
                tools = listOf(
                    ToolItem("مدقة كوكتيل", "Muddler", "لهرس الليمون والنعناع", "Muddle citrus and herbs"),
                    ToolItem("ملعقة بار حلزونية", "Bar Spoon", "للتقليب والطبقات", "For stirring"),
                    ToolItem("كوب 16 أونصة شفاف", "Clear 16oz Tumbler", "لإبراز اللون الأزرق الساحر", "To display electric blue color")
                ),
                ingredients = listOf(
                    IngredientItem("سيرب بلو كوراكاو حمضي (Blue Curaçao)", "Blue Curaçao Citrus Syrup", 30f, "مل (3 مضخات)", "ml (3 pumps)", pumpCount = 3),
                    IngredientItem("قطع ليمون لايم أخضر", "Fresh Lime Wedges", 4f, "قطع", "wedges", isScalable = false),
                    IngredientItem("أوراق نعناع طازج", "Fresh Mint Leaves", 8f, "أوراق", "leaves", isScalable = false),
                    IngredientItem("ثلج مجروش ناصع", "Crisp Crushed Ice", 180f, "جم", "g"),
                    IngredientItem("مياه صودا غازية فوارة مثلجة (سفن أب أو سبرايت)", "Chilled Sprite / Sparkling Soda", 180f, "مل", "ml"),
                    IngredientItem("شريحة ليمون لايم وغصن نعناع", "Lime Wheel & Mint Crown", 1f, "للتزيين", "for garnish", isScalable = false)
                ),
                steps = listOf(
                    PrepStep(1, "هرس الليمون والنعناع مع سيرب الكوراكاو", "Muddle with Blue Syrup", "ضع سيرب البلو كوراكاو الأزرق وقطع الليمون في الكوب واهرس لاستخراج عصير الليمون، ثم أضف النعناع واضغط برفق.", "Pour vibrant blue curaçao syrup and lime wedges into glass. Muddle lime, add mint, and press gently.", 20),
                    PrepStep(2, "ملء الكوب بالثلج المجروش", "Pack Crushed Ice", "املأ الكوب بالكامل بالثلج المجروش وحرّك الملعقة قليلاً في القاع.", "Fill glass completely with crushed ice and stir slightly at base.", 15),
                    PrepStep(3, "إضافة الصودا لمشهد المحيط", "Add Soda for Ocean Effect", "اسكب الصودا الغازية الباردة ببطء لتشاهد التدرج اللوني الأزرق الفيروزي المضيء مثل مياه المحيط.", "Pour chilled fizzy soda slowly, creating an illuminating turquoise ocean gradient.", 15),
                    PrepStep(4, "التقديم", "Garnish & Enjoy", "زيّن بشريحة ليمون أخضر وغصن نعناع مع شاليموه أسود أنيق.", "Crown with lime wheel, fragrant mint sprig, and sleek black straw.", 10)
                ),
                baristaTips = listOf(
                    BaristaTip("نكهة البلو كوراكاو", "What is Blue Curaçao?", "البلو كوراكاو الأصلي نكهته قشر برتقال لاهارا الحمضي المميز وليس توتاً؛ توازنه مع الليمون الأخضر يعطي انتعاشاً استوائياً فائقاً.", "Blue Curaçao syrup carries aromatic orange peel notes, complementing tart limes perfectly.")
                ),
                tags = listOf("بلو أوشن", "بلو كوراكاو", "blue ocean", "mojito", "موهيتو", "أزرق", "mojitos"),
                isSignature = true
            )
        )
    }

    fun getDrinkById(id: String): Drink? {
        return allDrinks.find { it.id == id }
    }

    fun getDrinksByCategory(category: DrinkCategory): List<Drink> {
        return allDrinks.filter { it.category == category }
    }

    fun searchDrinks(query: String, selectedCategory: DrinkCategory? = null): List<Drink> {
        val trimmed = query.trim().lowercase()
        return allDrinks.filter { drink ->
            val matchesCategory = selectedCategory == null || drink.category == selectedCategory
            if (!matchesCategory) return@filter false

            if (trimmed.isEmpty()) return@filter true

            drink.nameAr.lowercase().contains(trimmed) ||
            drink.nameEn.lowercase().contains(trimmed) ||
            drink.descriptionAr.lowercase().contains(trimmed) ||
            drink.descriptionEn.lowercase().contains(trimmed) ||
            drink.tags.any { it.lowercase().contains(trimmed) } ||
            drink.ingredients.any { it.nameAr.lowercase().contains(trimmed) || it.nameEn.lowercase().contains(trimmed) }
        }
    }

    // Smart query analyzer for custom orders (e.g. "Iced Spanish Latte Extra Caramel")
    data class CustomOrderAnalysis(
        val matchedDrink: Drink,
        val customModifiersAr: List<String>,
        val customModifiersEn: List<String>,
        val suggestedPumpAdjustments: Map<String, Int> = emptyMap()
    )

    fun analyzeCustomOrder(query: String): CustomOrderAnalysis? {
        val trimmed = query.trim().lowercase()
        if (trimmed.isEmpty()) return null

        val words = trimmed.split(" ").filter { it.length >= 3 }
        val candidate = allDrinks.map { drink ->
            val tokens = (drink.nameEn.lowercase().split(" ") + drink.tags.map { it.lowercase() })
            val score = words.count { word -> tokens.any { it == word || it.contains(word) || word.contains(it) } } +
                    if (drink.nameAr.contains(trimmed) || trimmed.contains(drink.nameAr)) 5 else 0
            Pair(drink, score)
        }.filter { it.second > 0 }.maxByOrNull { it.second }?.first ?: return null

        val modAr = mutableListOf<String>()
        val modEn = mutableListOf<String>()
        val pumps = mutableMapOf<String, Int>()

        if (trimmed.contains("extra caramel") || trimmed.contains("كراميل زيادة") || trimmed.contains("إكسترا كراميل")) {
            modAr.add("+ 1 مضخة إضافية صوص كراميل (10 مل) وتزيين الجدار")
            modEn.add("+ 1 Extra pump caramel drizzle & cup walls")
            pumps["Caramel"] = 1
        }
        if (trimmed.contains("extra vanilla") || trimmed.contains("فانيليا زيادة")) {
            modAr.add("+ 1 مضخة فانيليا إضافية")
            modEn.add("+ 1 Extra pump vanilla syrup")
            pumps["Vanilla"] = 1
        }
        if (trimmed.contains("oat milk") || trimmed.contains("حليب شوفان")) {
            modAr.add("استبدال بحليب الشوفان الباريستا")
            modEn.add("Swap to Barista Oat Milk")
        }
        if (trimmed.contains("almond milk") || trimmed.contains("حليب لوز")) {
            modAr.add("استبدال بحليب اللوز الخفيف")
            modEn.add("Swap to Almond Milk")
        }
        if (trimmed.contains("extra shot") || trimmed.contains("شوت زيادة") || trimmed.contains("دبل شوت إضافي")) {
            modAr.add("+ 1 شوت إسبريسو إضافي (+19 جم)")
            modEn.add("+ 1 Extra espresso shot (+19g)")
        }
        if (trimmed.contains("less sweet") || trimmed.contains("سكر خفيف") || trimmed.contains("حلاوة خفيفة")) {
            modAr.add("تخفيض السيرب / الحليب المكثف إلى النصف (50% Sweetness)")
            modEn.add("Half-sweet: reduce syrups / condensed milk by 50%")
        }

        return CustomOrderAnalysis(
            matchedDrink = candidate,
            customModifiersAr = modAr,
            customModifiersEn = modEn,
            suggestedPumpAdjustments = pumps
        )
    }
}
