package org.snippet.springbootstarter.health

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthRoutes {

    @GetMapping("")
    fun ping(): ResponseEntity<Unit> = ResponseEntity.ok().build()
}