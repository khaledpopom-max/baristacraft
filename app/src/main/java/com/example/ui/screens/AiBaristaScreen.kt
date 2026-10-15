package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ai.ChatMessage
import com.example.ai.GeminiBaristaClient
import com.example.ai.MessageSender
import com.example.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun AiBaristaScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val clipboardManager = LocalClipboardManager.current

    val initialGreeting = remember {
        ChatMessage(
            sender = MessageSender.BARISTA_AI,
            text = """
مرحباً بك في ركن باريستا الذكاء الاصطناعي! ☕✨
أنا "Master Barista AI" — مستشارك وخبير تحضير المشروبات وتدريب الباريستا المحترف.

كيف يمكنني مساعدتك اليوم؟
• معايرة طحنة الإسبريسو وضبط الاستخلاص (Dialing in)
• ابتكار مشروب استثنائي بناءً على المكونات المتوفرة لديك
• تعديل وتخصيص أي وصفة (سيرب، حليب الشوفان، شوت إضافي)
• تشخيص مشاكل التحضير (رغوة الحليب، حموضة القهوة، الموهيتو)

Welcome to Master Barista AI! Ask me anything about coffee calibration, drink crafting, or custom orders!
            """.trimIndent()
        )
    }

    val messages = remember { mutableStateListOf(initialGreeting) }
    var inputText by remember { mutableStateOf("") }
    var isThinking by remember { mutableStateOf(false) }

    fun sendUserPrompt(prompt: String) {
        val trimmed = prompt.trim()
        if (trimmed.isEmpty() || isThinking) return

        val userMessage = ChatMessage(
            sender = MessageSender.USER,
            text = trimmed
        )
        messages.add(userMessage)
        inputText = ""
        isThinking = true

        coroutineScope.launch {
            // Scroll to bottom
            listState.animateScrollToItem(messages.size - 1)

            val reply = GeminiBaristaClient.sendMessage(messages, trimmed)
            messages.add(
                ChatMessage(
                    sender = MessageSender.BARISTA_AI,
                    text = reply
                )
            )
            isThinking = false
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    val quickActionChips = listOf(
        Pair("كيف أضبط طحنة الإسبريسو؟", "How do I dial in espresso?"),
        Pair("تعديل مكونات مشروبي", "Customize my drink with oat milk"),
        Pair("اقترح مشروباً حسب مكوناتي", "Suggest a drink with my ingredients"),
        Pair("لماذا رغوة الحليب غير متماسكة؟", "Why is my milk foam watery?")
    )

    Scaffold(
        topBar = {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 3.dp,
                border = CardDefaults.outlinedCardBorder()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = onNavigateBack,
                            modifier = Modifier.testTag("ai_barista_back_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        // Avatar with active badge
                        Box {
                            Surface(
                                shape = CircleShape,
                                color = CoffeeEspresso,
                                modifier = Modifier.size(42.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.SmartToy,
                                        contentDescription = "Barista AI",
                                        tint = CoffeeLatteCream,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                            // Online indicator dot
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF4CAF50))
                                    .align(Alignment.BottomEnd)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Master Barista AI",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = " • ",
                                    color = CoffeeCaramel,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "باريستا ذكي",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = CoffeeCaramel
                                )
                            }
                            Text(
                                text = "مستشار ومدرّب المشروبات • Active",
                                fontSize = 11.sp,
                                color = Color(0xFF2E7D32)
                            )
                        }
                    }

                    // Clear Chat Button
                    IconButton(
                        onClick = {
                            messages.clear()
                            messages.add(initialGreeting)
                        },
                        modifier = Modifier.testTag("ai_clear_chat_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.DeleteOutline,
                            contentDescription = "Clear Chat",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        },
        bottomBar = {
            // Persistent Input Bar
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp,
                border = CardDefaults.outlinedCardBorder(),
                modifier = Modifier.navigationBarsPadding()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    // Quick Action Chips Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        quickActionChips.forEach { (ar, en) ->
                            SuggestionChip(
                                onClick = { sendUserPrompt(ar) },
                                label = {
                                    Text(
                                        text = "$ar / $en",
                                        fontSize = 11.sp,
                                        maxLines = 1
                                    )
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = SuggestionChipDefaults.suggestionChipColors(
                                    containerColor = CoffeeLatteCream.copy(alpha = 0.35f)
                                ),
                                modifier = Modifier.testTag("quick_chip_${ar.take(4)}")
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Text Input Field & Send Button
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            placeholder = {
                                Text(
                                    text = "اسأل عن أي مشروب، وصفة، أو تعديل...",
                                    fontSize = 13.sp
                                )
                            },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("ai_chat_input_field"),
                            shape = RoundedCornerShape(22.dp),
                            maxLines = 4,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = CoffeeCaramel,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        FilledIconButton(
                            onClick = { sendUserPrompt(inputText) },
                            enabled = inputText.isNotBlank() && !isThinking,
                            colors = IconButtonDefaults.filledIconButtonColors(
                                containerColor = CoffeeCaramel,
                                disabledContainerColor = CoffeeCaramel.copy(alpha = 0.4f)
                            ),
                            modifier = Modifier
                                .size(48.dp)
                                .testTag("ai_chat_send_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Send,
                                contentDescription = "Send",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        },
        modifier = modifier.testTag("ai_barista_screen")
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
        ) {
            items(messages, key = { it.id }) { message ->
                ChatBubble(
                    message = message,
                    onCopyText = { text ->
                        clipboardManager.setText(AnnotatedString(text))
                    }
                )
            }

            // Thinking Animation Indicator
            if (isThinking) {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .testTag("ai_thinking_indicator")
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = CoffeeEspresso,
                            modifier = Modifier.size(30.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.SmartToy,
                                    contentDescription = null,
                                    tint = CoffeeLatteCream,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(14.dp),
                                    strokeWidth = 2.dp,
                                    color = CoffeeCaramel
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "جارٍ استشارة Master Barista AI...",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChatBubble(
    message: ChatMessage,
    onCopyText: (String) -> Unit
) {
    val isUser = message.sender == MessageSender.USER

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        if (!isUser) {
            Surface(
                shape = CircleShape,
                color = CoffeeEspresso,
                modifier = Modifier
                    .size(32.dp)
                    .padding(top = 2.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.LocalCafe,
                        contentDescription = "AI",
                        tint = CoffeeLatteCream,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        Surface(
            shape = RoundedCornerShape(
                topStart = 18.dp,
                topEnd = 18.dp,
                bottomStart = if (isUser) 18.dp else 4.dp,
                bottomEnd = if (isUser) 4.dp else 18.dp
            ),
            color = if (isUser) CoffeeCaramel else MaterialTheme.colorScheme.surface,
            border = if (!isUser) CardDefaults.outlinedCardBorder() else null,
            tonalElevation = if (!isUser) 2.dp else 0.dp,
            modifier = Modifier.widthIn(max = 320.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                // Header tag for AI
                if (!isUser) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Master Barista AI • خبير التحضير",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = CoffeeCaramel
                        )
                        IconButton(
                            onClick = { onCopyText(message.text) },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copy",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }

                // Message Text
                Text(
                    text = message.text,
                    fontSize = 14.sp,
                    color = if (isUser) Color.White else MaterialTheme.colorScheme.onSurface,
                    lineHeight = 20.sp
                )
            }
        }
    }
}
