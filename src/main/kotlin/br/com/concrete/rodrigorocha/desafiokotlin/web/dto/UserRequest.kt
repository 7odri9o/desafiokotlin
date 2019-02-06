package br.com.concrete.rodrigorocha.desafiokotlin.web.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class UserRequest(
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
    val phones: List<PhoneRequest>? = null)