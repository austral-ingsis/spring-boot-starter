package org.snippet.springbootstarter.album

import jakarta.transaction.Transactional
import org.snippet.springbootstarter.album.AlbumRoutes.*
import org.snippet.springbootstarter.album.errors.UnknownArtistException
import org.snippet.springbootstarter.external.wikipedia.WikipediaApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class AlbumService @Autowired constructor(private val repository: AlbumRepository, private val wikipedia: WikipediaApi) {

    fun getAlbums(artist: String?): List<Album> {
        val albums = if (artist == null) repository.findAll()
        else repository.findByArtist(artist)
        return albums.map { translate(it) }
    }

    fun getAlbum(id: Long): Album? {
        return repository.findById(id).getOrNull()?.let { translate(it) }
    }

    fun createAlbum(album: AlbumRequest): Album {
        val dbAlbum = translate(album)
        if (isKnownArtist(dbAlbum)) {
            val created = repository.save(dbAlbum)
            return translate(created)
        } else {
            throw UnknownArtistException(album.artist)
        }
    }

    private fun isKnownArtist(dbAlbum: DbAlbum) = wikipedia.findArtist(dbAlbum.artist)

    fun deleteAlbum(id: Long) {
        return repository.deleteById(id)
    }

    @Transactional // Spring Hibernate needs all the repository calls in this method to be in a transaction to be able to delete the repository
    fun deleteAlbumsOf(artist: String) {
        repository.deleteByArtistIs(artist)
    }

    private fun translate(album: AlbumRequest): DbAlbum {
        return DbAlbum(null, album.name, album.publishedDate, album.artist)
    }

    private fun translate(dbAlbum: DbAlbum): Album {
        return Album(dbAlbum.id, dbAlbum.name, dbAlbum.publishedDate, dbAlbum.artist)
    }
}