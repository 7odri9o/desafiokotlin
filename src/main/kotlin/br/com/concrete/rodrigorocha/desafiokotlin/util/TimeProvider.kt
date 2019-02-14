package br.com.concrete.rodrigorocha.desafiokotlin

import org.joda.time.DateTime

class TimeProvider {

    fun now() : DateTime {
        return DateTime.now()
    }
}