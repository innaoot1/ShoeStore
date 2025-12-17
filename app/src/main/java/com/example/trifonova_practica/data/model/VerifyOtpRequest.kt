package com.example.trifonova_practica.data.model

data class VerifyOtpRequest(
    val email: String,
    val token: String,
    val type: String = "email"
)