package br.com.concrete.rodrigorocha.desafiokotlin.domain.dto

data class Phone(
    val id: Long? = null,
    val ddd: String,
    val number: String
)