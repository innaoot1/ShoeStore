package com.example.trifonova_practica.data.service

import com.example.trifonova_practica.data.model.SignInRequest
import com.example.trifonova_practica.data.model.SignInResponse
import com.example.trifonova_practica.data.model.SignUpRequest
import com.example.trifonova_practica.data.model.SignUpResponse
import com.example.trifonova_practica.data.model.VerifyOtpRequest
import com.example.trifonova_practica.data.model.VerifyOtpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

const val API_KEY="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImV1dGpkZ2dpem92aWJtemphdm5vIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjQ2MTA4MTcsImV4cCI6MjA4MDE4NjgxN30.2S7E8a_guM1LnmJmk9jKefcfZE4HZ3IopbqCCtrKt9I"

interface UserManagementService {

    @Headers(
        "apikey: $API_KEY",
        "Content-Type: application/json"
    )
    @POST("auth/v1/signup")
    suspend fun signUp(@Body signUpRequest: SignUpRequest): Response<SignUpResponse>

    @Headers(
        "apikey: $API_KEY",
        "Content-Type: application/json"
    )
    @POST("auth/v1/token?grant_type=password")
    suspend fun signIn(@Body signInRequest: SignInRequest): Response<SignInResponse>

    @Headers(
        "apikey: $API_KEY",
        "Content-Type: application/json"
    )
    @POST("auth/v1/verify")
    suspend fun verifyOtp(@Body verifyOtpRequest: VerifyOtpRequest): Response<VerifyOtpResponse>
}