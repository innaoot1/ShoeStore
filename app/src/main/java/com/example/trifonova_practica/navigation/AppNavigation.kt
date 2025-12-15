package com.example.trifonova_practica.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trifonova_practica.screens.LoginScreen
import com.example.trifonova_practica.screens.RegisterScreen


/**
 * Навигационный граф приложения.
 * Определяет все экраны и переходы между ними.
 *
 * @author Ваше Имя
 * @created 15.12.2024
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "register"
    ) {
        // Экран регистрации
        composable("register") {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("register") { saveState = true }
                    }
                },
                onRegisterSuccess = {
                    // После успешной регистрации переходим на логин
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        // Экран входа (добавьте позже)
        composable("login") {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate("register") {
                        popUpTo("login") { saveState = true }
                    }
                },
                onLoginSuccess = {
                    // Переход на главный экран после входа
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
    }
}