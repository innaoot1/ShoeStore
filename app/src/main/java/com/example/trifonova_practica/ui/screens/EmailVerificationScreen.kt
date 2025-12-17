package com.example.trifonova_practica.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shoeshop.R
import com.example.trifonova_practica.ui.components.DisableButton
import com.example.trifonova_practica.ui.viewmodel.EmailVerificationViewModel
import com.example.trifonova_practica.ui.viewmodel.VerificationState
import com.example.trifonova_practica.ui.theme.AppTypography
import kotlin.text.ifEmpty

@Composable
fun EmailVerificationScreen(
    onSignInClick: () -> Unit,
    onVerificationSuccess: () -> Unit,
    viewModel: EmailVerificationViewModel = viewModel()
) {
    var otpCode by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    val context = LocalContext.current
    val verificationState by viewModel.verificationState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        userEmail = getUserEmail(context)
    }

    LaunchedEffect(verificationState) {
        when (verificationState) {
            is VerificationState.Success -> {
                onVerificationSuccess()
                viewModel.resetState()
            }
            is VerificationState.Error -> {
                val errorMessage = (verificationState as VerificationState.Error).message
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                viewModel.resetState()
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Заголовок
        Text(
            text = "Верифицируйте свою почту",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Информационное сообщение
        Text(
            text = "Мы отправили 6-значный проверочный код по адресу",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Email пользователя
        Text(
            text = userEmail.ifEmpty { "Ваш email" },
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF0560FA),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        // Поле для OTP кода
        Text(
            text = "Получить OTP Код",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = otpCode,
            onValueChange = {
                if (it.length <= 6 && it.all { char -> char.isDigit() }) {
                    otpCode = it
                }
            },
            placeholder = { Text("123456") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFA7A7A7),
                focusedBorderColor = Color(0xFF0560FA)
            )
        )

        DisableButton(
            text = stringResource(id = R.string.verify),
            onClick = {
                if (otpCode.length == 6 && userEmail.isNotEmpty()) {
                    viewModel.verifyOtp(userEmail, otpCode)
                } else if (userEmail.isEmpty()) {
                    Toast.makeText(context, "Адрес электронной почты не найден. Пожалуйста, зарегистрируйтесь еще раз.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Пожалуйста введите OTP code", Toast.LENGTH_LONG).show()
                }
            },
            textStyle = AppTypography.bodyMedium16
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Ссылка для возврата к входу
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TextButton(
                onClick = onSignInClick,
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color(0xFFA7A7A7))) {
                            append("Уже верифицировали? ")
                        }
                        withStyle(style = SpanStyle(color = Color(0xFFA7A7A7))) {
                            append("Вход")
                        }
                    },
                    fontSize = 14.sp
                )
            }
        }
    }
}

fun getUserEmail(context: Context): String {
    val sharedPreferences = context.getSharedPreferences("shoe_shop_prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("user_email", "") ?: ""
}