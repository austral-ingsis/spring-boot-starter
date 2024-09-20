package org.snippet.springbootstarter.album

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.time.LocalDate

@RestController
class AlbumRoutes @Autowired constructor(private val service: AlbumService) : AlbumRoutesSpec {

    data class AlbumRequest(val name: String, val publishedDate: LocalDate, val artist: String)

    override fun getAlbums(@RequestParam artist: String?): List<Album> = service.getAlbums(artist)

    override fun getAlbum(@PathVariable id: Long): Album? = service.getAlbum(id)

    override fun createAlbum(@RequestBody album: AlbumRequest): ResponseEntity<Album> {
        val created = service.createAlbum(album)
        return ResponseEntity.created(URI("/v1/albums/${created.id}")).body(created)
    }

    override fun deleteAlbum(@PathVariable id: Long) = service.deleteAlbum(id)

    override fun deleteAlbums(artist: String) = service.deleteAlbumsOf(artist)
}