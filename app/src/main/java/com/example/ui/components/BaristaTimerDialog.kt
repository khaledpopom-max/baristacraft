package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Timer
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
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.CoffeeCaramel
import com.example.ui.theme.CoffeeDark
import com.example.ui.theme.CoffeeEspresso
import kotlinx.coroutines.delay

@Composable
fun BaristaTimerDialog(
    initialSeconds: Int = 28,
    timerLabelAr: String = "مؤقت استخلاص الإسبريسو",
    timerLabelEn: String = "Espresso Shot Extraction Timer",
    onDismiss: () -> Unit
) {
    var targetSeconds by remember { mutableIntStateOf(initialSeconds) }
    var remainingSeconds by remember { mutableIntStateOf(initialSeconds) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning, remainingSeconds) {
        if (isRunning && remainingSeconds > 0) {
            delay(1000L)
            remainingSeconds -= 1
        } else if (remainingSeconds == 0) {
            isRunning = false
        }
    }

    val progress by animateFloatAsState(
        targetValue = if (targetSeconds > 0) remainingSeconds.toFloat() / targetSeconds.toFloat() else 0f,
        label = "timerProgress"
    )

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .testTag("barista_timer_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header with Close
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = "Timer",
                            tint = CoffeeCaramel,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = timerLabelAr,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Text(
                                text = timerLabelEn,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("timer_close_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Circular Timer Display
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(180.dp)
                ) {
                    CircularProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.fillMaxSize(),
                        strokeWidth = 10.dp,
                        color = if (remainingSeconds > 5) CoffeeCaramel else Color(0xFFE53935),
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = String.format("%02d:%02d", remainingSeconds / 60, remainingSeconds % 60),
                            fontSize = 42.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = if (isRunning) "جارٍ الحساب • Running" else if (remainingSeconds == 0) "تم بنجاح! • Completed!" else "جاهز • Ready",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (remainingSeconds == 0) Color(0xFF2E7D32) else CoffeeCaramel
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Presets (Shot 28s, Steam 18s, Shake 15s)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FilterChip(
                        selected = targetSeconds == 28,
                        onClick = {
                            targetSeconds = 28
                            remainingSeconds = 28
                            isRunning = false
                        },
                        label = { Text("إسبريسو 28s") },
                        modifier = Modifier.testTag("timer_preset_espresso")
                    )
                    FilterChip(
                        selected = targetSeconds == 20,
                        onClick = {
                            targetSeconds = 20
                            remainingSeconds = 20
                            isRunning = false
                        },
                        label = { Text("تبخير 20s") },
                        modifier = Modifier.testTag("timer_preset_steam")
                    )
                    FilterChip(
                        selected = targetSeconds == 15,
                        onClick = {
                            targetSeconds = 15
                            remainingSeconds = 15
                            isRunning = false
                        },
                        label = { Text("خفق 15s") },
                        modifier = Modifier.testTag("timer_preset_shake")
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Play / Pause / Reset Controls
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Reset
                    FilledTonalIconButton(
                        onClick = {
                            isRunning = false
                            remainingSeconds = targetSeconds
                        },
                        modifier = Modifier.size(52.dp).testTag("timer_reset_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Reset"
                        )
                    }

                    // Main Start/Pause Button
                    Button(
                        onClick = {
                            if (remainingSeconds == 0) remainingSeconds = targetSeconds
                            isRunning = !isRunning
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = CoffeeCaramel
                        ),
                        modifier = Modifier
                            .height(52.dp)
                            .testTag("timer_toggle_button")
                    ) {
                        Icon(
                            imageVector = if (isRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isRunning) "Pause" else "Start"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isRunning) "إيقاف / Pause" else "ابدأ / Start",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}
