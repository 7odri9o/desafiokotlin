package br.com.concrete.rodrigorocha.desafiokotlin.util

import com.mashape.unirest.http.ObjectMapper
import com.mashape.unirest.http.Unirest
import com.mashape.unirest.http.Unirest.setObjectMapper
import io.javalin.core.util.Header
import io.javalin.json.JavalinJson
import io.javalin.json.JavalinJson.fromJson

class HttpUtil(port: Int) {

    private val applicationJson = "application/json"

    val headers = mutableMapOf(
        Header.ACCEPT to applicationJson,
        Header.CONTENT_TYPE to applicationJson)

    init {
        setObjectMapper(object : ObjectMapper {

            override fun <T> readValue(value: String, valueType: Class<T>): T {
                return fromJson(value, valueType)
            }

            override fun writeValue(value: Any): String {
                return JavalinJson.toJson(value)
            }
        })
    }

    @JvmField
    val origin: String = "http://localhost:$port"

    inline fun <reified T> post(path: String) =
            Unirest.post("$origin$path").headers(headers).asObject(T::class.java)

    inline fun <reified T> post(path: String, body: Any) =
        Unirest.post("$origin$path").headers(headers).body(body).asObject(T::class.java)

    inline fun <reified T> get(path: String, params: Map<String, Any>? = null) =
            Unirest.get("$origin$path").headers(headers).asObject(T::class.java)

    inline fun <reified T> put(path: String, body: Any) =
            Unirest.put("$origin$path").headers(headers).body(body).asObject(T::class.java)
    
}