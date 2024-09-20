package org.snippet.springbootstarter.external.wikipedia

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
class WikipediaApiConfiguration{

    /**
     * The @Bean annotation in a method allows us to create an instance of the type it returns everywhere it is injected by the @Autowire annotation
     */
    @Profile("!test")
    @Bean
    fun createWikipediaApi(@Value("\${services.wikipedia.host}") host: String): WikipediaApi {
        return object: WikipediaApi {
            override fun findArtist(name: String): Boolean = true
        }
    }
}