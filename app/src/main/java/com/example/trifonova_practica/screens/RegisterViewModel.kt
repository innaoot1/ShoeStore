package com.example.trifonova_practica.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    // Состояния для полей ввода
    var name by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    // Состояние видимости пароля
    var passwordVisible by mutableStateOf(false)
        private set

    // Состояние согласия с условиями
    var termsAccepted by mutableStateOf(false)
        private set

    // Состояния ошибок валидации
    var nameError by mutableStateOf<String?>(null)
        private set

    var emailError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    var termsError by mutableStateOf<String?>(null)
        private set

    // Состояние загрузки
    var isLoading by mutableStateOf(false)
        private set

    // Функции для обновления состояний
    fun updateName(newName: String) {
        name = newName
        nameError = null // Сбрасываем ошибку при изменении
    }

    fun updateEmail(newEmail: String) {
        email = newEmail
        emailError = null
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
        passwordError = null
    }

    fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
    }

    fun toggleTermsAccepted() {
        termsAccepted = !termsAccepted
        termsError = null
    }

    // Валидация email
    private fun isValidEmail(email: String): Boolean {
        val pattern = "^[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}$".toRegex()
        return pattern.matches(email)
    }

    // Валидация всех полей
    private fun validateForm(): Boolean {
        var isValid = true

        if (name.isBlank()) {
            nameError = "Поле не может быть пустым"
            isValid = false
        }

        if (email.isBlank()) {
            emailError = "Поле не может быть пустым"
            isValid = false
        } else if (!isValidEmail(email)) {
            emailError = "Неверный формат email"
            isValid = false
        }

        if (password.isBlank()) {
            passwordError = "Поле не может быть пустым"
            isValid = false
        } else if (password.length < 6) {
            passwordError = "Пароль должен содержать минимум 6 символов"
            isValid = false
        }

        if (!termsAccepted) {
            termsError = "Необходимо согласие с условиями"
            isValid = false
        }

        return isValid
    }

    // Функция регистрации (заглушка - в реальном проекте здесь работа с API)
    fun register(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (!validateForm()) {
            return
        }

        viewModelScope.launch {
            try {
                isLoading = true
                // Имитация сетевого запроса
                delay(2000)

                // В реальном проекте здесь вызов API:
                // val result = authRepository.register(name, email, password)

                // Заглушка успешной регистрации
                onSuccess()
            } catch (e: Exception) {
                onError("Ошибка регистрации: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }
}