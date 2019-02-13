package br.com.concrete.rodrigorocha.desafiokotlin.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.joda.time.DateTime

data class UserDTO(
    var id: Long? = null,
    var name: String? = null,
    var email: String? = null,
    var phones: List<PhoneDTO>? = listOf(),
    @JsonIgnore var password: String? = null,

    var created: DateTime? = null,
    var modified: DateTime? = null,
    var token: String? = null,
    @JsonProperty(value = "last_login") var lastLogin: DateTime? = null)