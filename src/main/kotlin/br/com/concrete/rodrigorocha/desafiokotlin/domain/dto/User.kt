package br.com.concrete.rodrigorocha.desafiokotlin.domain.dto

import org.joda.time.DateTime

data class User(
    var id: Long? = null,
    val name: String,
    val email: String,
    var password: String? = null,
    var created: DateTime? = null,
    var modified: DateTime? = null,
    var last_login: DateTime? = null,
    var token: String? = null)