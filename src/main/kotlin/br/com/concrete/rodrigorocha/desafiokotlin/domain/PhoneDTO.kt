package br.com.concrete.rodrigorocha.desafiokotlin.domain

import com.fasterxml.jackson.annotation.JsonIgnore

data class PhoneDTO(
    @JsonIgnore var id: Long? = null,
    val ddd: String,
    val number: String)