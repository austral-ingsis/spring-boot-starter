package org.snippet.springbootstarter

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.snippet.springbootstarter.album.Album
import org.snippet.springbootstarter.album.AlbumRepository
import org.snippet.springbootstarter.album.AlbumRoutes.AlbumRequest
import org.snippet.springbootstarter.fixtures.AlbumFixtures
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.LocalDate


/**
 * Example E2E Test that you can use to test your services
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // starts your spring server in a random port
@ExtendWith(SpringExtension::class)
@ActiveProfiles(value = ["test"])
@AutoConfigureWebTestClient // sets up an adecuate 'WebTestClient'
class AlbumRequestE2ETests @Autowired constructor(
    val client: WebTestClient,
    val repository: AlbumRepository
) {

    private val BASE = "/v1/albums"

    @BeforeEach
    fun setup() {
        repository.saveAll(AlbumFixtures.all())
    }

    @Test
    fun `can get all albums`() {
        getAllAlbums()
            .expectBodyList(Album::class.java)
            .hasSize(6)
    }

    @Test
    fun `can get all albums of an artist`() {
        val response = getAlbumsOf("Divididos")

        val list = response.expectBodyList(Album::class.java)

        list.hasSize(1)

        list
            .returnResult()
            .responseBody!!
            .first()
            .name shouldBeEqualTo "Acariciando lo áspero"
    }

    @Test
    fun `can create an album`() {
        val request = AlbumRequest("Tell Your Friends", LocalDate.of(2009, 11, 21), "Snarky Puppy")
        createAlbum(request).expectStatus().isCreated

        val response = getAllAlbums()

        val list = response.expectBodyList(Album::class.java)

        list.hasSize(7)
    }

    @Test
    fun `cannot create an album of an unknown artist`() {
        val request = AlbumRequest("29 de Febrero", LocalDate.of(2024, 2, 14), "Morat")
        createAlbum(request).expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value())

        val response = getAllAlbums()

        val list = response.expectBodyList(Album::class.java)

        list.hasSize(6)
    }

    @Test
    fun `can delete an album of a specific artist`() {
        deleteAlbumsOf("Shakira")
        val response = getAlbumsOf("Shakira")
        response.expectBodyList(Album::class.java)
            .hasSize(0)
    }

    @AfterEach
    fun tearDown() {
        repository.deleteAll()
    }

    private fun createAlbum(request: AlbumRequest): WebTestClient.ResponseSpec {
        return client.post().uri(BASE).bodyValue(request)
            .exchange()
    }

    private fun getAlbumsOf(artist: String): WebTestClient.ResponseSpec {
        return client.get().uri("$BASE?artist=$artist")
            .exchange()
            .expectStatus().isOk
    }

    private fun getAllAlbums(): WebTestClient.ResponseSpec {
        return client.get().uri(BASE)
            .exchange()
            .expectStatus().isOk
    }

    private fun deleteAlbumsOf(artist: String): WebTestClient.ResponseSpec {
        return client.delete().uri("$BASE?artist=$artist").exchange().expectStatus().isOk
    }
}
