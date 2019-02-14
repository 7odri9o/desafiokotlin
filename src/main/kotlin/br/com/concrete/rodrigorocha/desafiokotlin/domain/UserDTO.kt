package br.com.concrete.rodrigorocha.desafiokotlin.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.joda.time.DateTime
import java.util.*

data class UserDTO(
    var id: UUID? = null,
    var name: String? = null,
    var email: String? = null,
    var phones: List<PhoneDTO>? = listOf(),
    @JsonIgnore var password: String? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    var created: DateTime? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    var modified: DateTime? = null,
    var token: String? = null,

    @JsonProperty(value = "last_login")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    var lastLogin: DateTime? = null)