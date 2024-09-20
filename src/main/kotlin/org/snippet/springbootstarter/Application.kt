package org.snippet.springbootstarter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AlbumsApplication

fun main(args: Array<String>) {
    runApplication<AlbumsApplication>(*args)
}
