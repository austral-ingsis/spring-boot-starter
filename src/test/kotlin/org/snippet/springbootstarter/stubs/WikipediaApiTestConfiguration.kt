package org.snippet.springbootstarter.stubs

import org.snippet.springbootstarter.external.wikipedia.WikipediaApi
import org.snippet.springbootstarter.fixtures.AlbumFixtures
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Profile("test")
@Configuration
class WikipediaApiTestConfiguration {

    @Bean
    @Primary
    fun createTestWikipediaApi(): WikipediaApi {
        return InMemoryWikipediaApi()
    }
}

class InMemoryWikipediaApi : WikipediaApi {

    private val knownArtists = AlbumFixtures.all().map { it.artist }.toSet()

    override fun findArtist(name: String): Boolean {
        return knownArtists.contains(name)
    }
}