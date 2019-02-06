package br.com.concrete.rodrigorocha.desafiokotlin.web.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class UserResponse(

    @JsonIgnore
    val id: Long? = null,
    val created: Date? = null,
    val modified: Date? = null,
    val last_login: Date? = null,
    val token: String? = null,
    val name: String? = null,
    val email: String? = null,
    val message: String? = null)