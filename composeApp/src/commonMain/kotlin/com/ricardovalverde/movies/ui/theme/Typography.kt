package com.ricardovalverde.movies.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import movies.composeapp.generated.resources.Nunito_Bold
import movies.composeapp.generated.resources.Nunito_Medium
import movies.composeapp.generated.resources.Nunito_Regular
import movies.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

val nunito: FontFamily
    @Composable get() = FontFamily(
        Font(Res.font.Nunito_Regular, FontWeight.Normal),
        Font(Res.font.Nunito_Medium, FontWeight.Medium),
        Font(Res.font.Nunito_Bold, FontWeight.Bold)
    )

@Composable
fun AppTypography() = Typography(

    displaySmall = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = nunito,
    ),

    headlineLarge = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = nunito,
    ),

    titleLarge = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = nunito,
    ),

    titleMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = nunito,
    ),

    titleSmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = nunito,
    ),

    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = nunito,
    ),

    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = nunito,
    ),

    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = nunito,
    ),


    )