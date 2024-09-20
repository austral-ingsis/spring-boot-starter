package org.snippet.springbootstarter.album

import java.time.LocalDate

data class Album(val id: Long?, val name: String, val publishedDate: LocalDate, val artist: String)