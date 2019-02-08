package br.com.concrete.rodrigorocha.desafiokotlin.domain.dto

import java.util.*

data class User(
    var id: Long? = null,
    val name: String,
    val email: String,
    var password: String? = null,
    val phones: List<Phone>? = null,
    var created: Date? = null,
    var modified: Date? = null,
    var last_login: Date? = null,
    var token: String? = null)