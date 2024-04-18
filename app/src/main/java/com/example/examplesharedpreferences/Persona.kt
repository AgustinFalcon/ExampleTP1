package com.example.examplesharedpreferences

import java.io.Serializable


data class Persona(
    val name: String,
    val email: String,
    val password: String
): Serializable
