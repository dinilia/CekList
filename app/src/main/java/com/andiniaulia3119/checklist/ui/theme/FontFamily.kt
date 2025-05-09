package com.andiniaulia3119.checklist.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.material3.Typography
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.andiniaulia3119.mobpro1.R

val customFontFamily = FontFamily(
    Font(R.font.ostrichrounded, weight = FontWeight.ExtraBold)
)

val typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 12.sp
    ),
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = typography,
        content = content
    )
}
