package br.com.concrete.rodrigorocha.desafiokotlin.web.dto

import br.com.concrete.rodrigorocha.desafiokotlin.domain.PhoneDTO
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class NewUserDTO(
    val name: String? = null,
    val email: String? = null,
    val phones: List<PhoneDTO> = listOf(),
    val password: String? = null)