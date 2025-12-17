package com.example.trifonova_practica.ui.screens

import android.R
import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@Composable
fun SolidColorScreenWithIcon() {
    // Полный экран заданного цвета
    Box(
        modifier = Modifier
            .fillMaxSize()  // занимает весь экран
            .background(Color.Blue)  // цвет фона (можно заменить на любой)
    ) {
        // Иконка по центру экрана
        Icon(
            imageVector = Icons.Default.Check,  // встроенная иконка (галочка)
            contentDescription = "Пример иконки",  // для доступности
            modifier = Modifier
                .size(48.dp)  // размер иконки
                .align(Alignment.Center),  // центрируем внутри Box
            tint = Color.White  // цвет иконки (белый)
        )

    Icon(
        imageVector = ImageVector.vectorResource(id = com.example.shoeshop.R.drawable.corsina),
    contentDescription = null,
    modifier = Modifier.size(48.dp),
    tint = Color.White
    )
}
}
