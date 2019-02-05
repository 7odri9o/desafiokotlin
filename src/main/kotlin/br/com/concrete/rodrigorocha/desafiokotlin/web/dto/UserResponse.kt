package br.com.concrete.rodrigorocha.desafiokotlin.web.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class UserResponse(
    val id: Long,
    val created: Date,
    val modified: Date? = null,
    val last_login: Date? = null,
    val token: String? = null,
    val name: String? = null,
    val email: String? = null)