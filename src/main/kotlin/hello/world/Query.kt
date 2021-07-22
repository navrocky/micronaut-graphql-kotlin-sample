package hello.world

import io.micronaut.core.annotation.Introspected

@Introspected
class Query {
    fun hello(name: String): String {
        return "Hello $name!"
    }
}