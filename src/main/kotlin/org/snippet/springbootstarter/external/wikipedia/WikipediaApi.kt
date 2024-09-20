package org.snippet.springbootstarter.external.wikipedia

interface WikipediaApi {

    fun findArtist(name: String): Boolean
}