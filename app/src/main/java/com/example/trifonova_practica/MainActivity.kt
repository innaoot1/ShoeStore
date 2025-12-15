package com.example.trifonova_practica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.yourapp.presentation.navigation.AppNavigation
import com.yourapp.presentation.theme.MyAppTheme

/**
 * Главная Activity приложения.
 * Устанавливает тему и навигационный граф.
 *
 * @author Ваше Имя
 * @created 15.12.2024
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyAppTheme {
                // Фон приложения
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Навигационный граф
                    AppNavigation()
                }
            }
        }
    }
}