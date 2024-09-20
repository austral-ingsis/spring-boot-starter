package org.snippet.springbootstarter.album

import org.snippet.springbootstarter.album.AlbumRoutes.AlbumRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus

@RequestMapping("/v1")
interface AlbumRoutesSpec {

    @GetMapping("/albums")
    fun getAlbums(@RequestParam artist: String?): List<Album>

    @GetMapping("/albums/{id}")
    fun getAlbum(@PathVariable id: Long): Album?

    @PostMapping("/albums")
    @ResponseStatus(code = HttpStatus.CREATED)
    fun createAlbum(@RequestBody album: AlbumRequest): ResponseEntity<Album>

    @DeleteMapping("/albums/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    fun deleteAlbum(@PathVariable id: Long)

    @DeleteMapping("/albums")
    @ResponseStatus(code = HttpStatus.OK)
    fun deleteAlbums(@RequestParam artist: String)
}