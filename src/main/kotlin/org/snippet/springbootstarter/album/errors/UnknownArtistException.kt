package org.snippet.springbootstarter.album.errors

class UnknownArtistException(artist: String) : Exception("Artist '$artist' isn't a known artist")