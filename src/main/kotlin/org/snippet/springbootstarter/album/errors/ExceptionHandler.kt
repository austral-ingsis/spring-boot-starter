package org.snippet.springbootstarter.album.errors

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class UnknownArtistHandler {
    @ExceptionHandler(UnknownArtistException::class)
    protected fun handle(
        ex: UnknownArtistException
    ): ResponseEntity<Any> {
        return ResponseEntity.unprocessableEntity().build()
    }
}