package dev.leonlatsch.avivchallange.theming.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
fun textStyleSmall(): TextStyle = TextStyle(
    color = Color.Black,
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal,
)

fun textStyleMedium(): TextStyle = TextStyle(
    color = Color.Black,
    fontSize = 18.sp,
    fontWeight = FontWeight.Normal,
)

fun textStyleBig(): TextStyle = TextStyle(
    color = Color.Black,
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold,
)