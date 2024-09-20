package org.snippet.springbootstarter.album

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Entity
class DbAlbum(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,
    val name: String,
    val publishedDate: LocalDate,
    val artist: String
)

@Repository
interface AlbumRepository : CrudRepository<DbAlbum, Long>{
    fun findByArtist(artist: String): List<DbAlbum>
    fun deleteByArtistIs(artist: String): List<DbAlbum>
}