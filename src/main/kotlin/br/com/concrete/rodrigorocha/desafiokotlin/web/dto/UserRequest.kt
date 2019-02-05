package br.com.concrete.rodrigorocha.desafiokotlin.web.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class UserRequest(
    val name: String,
    val email: String,
    val password: String?,
    val phones: List<PhoneRequest>?
)