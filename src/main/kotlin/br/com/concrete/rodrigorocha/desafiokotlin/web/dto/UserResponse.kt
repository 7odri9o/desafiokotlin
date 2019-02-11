package br.com.concrete.rodrigorocha.desafiokotlin.web.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class UserResponse(
    val id: Long,
    val created: String,
    val modified: String,
    val last_login: String,
    val token: String,
    val name: String,
    val email: String)