package org.snippet.springbootstarter.fixtures

import org.snippet.springbootstarter.album.AlbumRoutes.AlbumRequest
import org.snippet.springbootstarter.album.DbAlbum
import java.time.LocalDate

/**
 * Just an object with some dummy data to run tests
 */

object AlbumFixtures {

    fun all(): List<DbAlbum> {
        return listOf(
            `MIRANDA - EL TEMPLO DEL POP`,
            `SHAKIRA - SALE EL SOL`,
            `DIVIDOS - ACARICIANDO LO ASPERO`,
            `HOZIER - UNREAL UNEARTH`,
            `CHARLY GARCIA - PIANO BAR`,
            `SNARKY PUPPY - EMPIRE CENTRAL`
        )
    }

    val `MIRANDA - EL TEMPLO DEL POP` = DbAlbum(1, "El Templo del Pop", LocalDate.of(2008, 4, 1), "Miranda")
    val `SHAKIRA - SALE EL SOL` = DbAlbum(2, "Sale el sol", LocalDate.of(2010, 10, 19), "Shakira")
    val `DIVIDOS - ACARICIANDO LO ASPERO` = DbAlbum(3, "Acariciando lo áspero", LocalDate.of(1991, 11, 15), "Divididos")
    val `HOZIER - UNREAL UNEARTH` = DbAlbum(4, "Unreal Unearth", LocalDate.of(2023, 8, 18), "Hozier")
    val `CHARLY GARCIA - PIANO BAR` = DbAlbum(5, "Piano Bar", LocalDate.of(1984, 1, 1), "Charly Garcia")
    val `SNARKY PUPPY - EMPIRE CENTRAL` = DbAlbum(6, "Empire Central", LocalDate.of(2022, 9, 30), "Snarky Puppy")
}