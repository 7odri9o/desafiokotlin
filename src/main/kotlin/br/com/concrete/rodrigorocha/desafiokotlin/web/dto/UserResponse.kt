package br.com.concrete.rodrigorocha.desafiokotlin.web.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class UserResponse(
    val id: Long,
    val created: Date,
    val modified: Date,
    val last_login: Date,
    val token: String,
    val name: String,
    val email: String)