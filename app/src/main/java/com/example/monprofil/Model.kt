package com.example.monprofil

import kotlinx.serialization.Serializable

data class TMDBListeDesFilms(
    val page: Int = 1,
    val results: List<AfficheDeFilm> = emptyList(),
    val total_pages: Int = 1,
    val total_results: Int = 0
)

data class AfficheDeFilm(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val genre_ids: List<Int> = emptyList(),
    val id: Int = 0,
    val media_type: String = "movie",
    val original_language: String = "fr",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)

@kotlinx.serialization.Serializable
data class TMDBListeDesSeries(
    val results: List<Serie>
)

@Serializable
data class Serie(
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String? // URL de l'image du poster
)

@Serializable
data class TMDBListeDesActeurs(
    val results: List<Acteur>
)

@Serializable
data class Acteur(
    val id: Int,
    val name: String,
    val profile_path: String? // URL de l'image du profil
)
@Serializable
data class TMDBCredits(
    val cast: List<Acteur> = emptyList()
)
@Serializable
data class TMDBCredits2(
    val cast: List<Acteur> = emptyList()
)


data class Playlist(
    val checksum: String,
    val collaborative: Boolean,
    val cover: String,
    val creation_date: String,
    val creator: Creator,
    val description: String,
    val duration: Int,
    val fans: Int,
    val id: Int,
    val is_loved_track: Boolean,
    val link: String,
    val md5_image: String,
    val nb_tracks: Int,
    val picture_type: String,
    val `public`: Boolean,
    val share: String,
    val title: String,
    val tracklist: String,
    val tracks: Tracks,
    val type: String
)

data class Creator(
    val id: Int,
    val name: String,
    val tracklist: String,
    val type: String
)

data class Tracks(
    val checksum: String,
    val `data`: List<Data>
)

data class Data(
    val album: Album,
    val artist: Artist,
    val duration: Int,
    val explicit_content_cover: Int,
    val explicit_content_lyrics: Int,
    val explicit_lyrics: Boolean,
    val id: Long,
    val isrc: String,
    val link: String,
    val md5_image: String,
    val preview: String,
    val rank: Int,
    val readable: Boolean,
    val time_add: Int,
    val title: String,
    val title_short: String,
    val title_version: String,
    val type: String
)

data class Album(
    val cover: String,
    val id: Int,
    val md5_image: String,
    val title: String,
    val tracklist: String,
    val type: String,
    val upc: String
)

data class Artist(
    val id: Int,
    val link: String,
    val name: String,
    val tracklist: String,
    val type: String
)