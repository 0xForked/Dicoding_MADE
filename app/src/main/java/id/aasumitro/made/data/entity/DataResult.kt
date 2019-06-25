package id.aasumitro.made.data.entity

/**
 * Created by A. A. Sumitro on 24/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

object DataResult {

    data class MovieResult(
        var page: Int? = null,
        var total_results: Long? = null,
        var total_pages: Long? = null,
        var results: List<Movie>? = emptyList()
    )

    data class ShowResult(
        var page: Int? = null,
        var total_results: Long? = null,
        var total_pages: Long? = null,
        var results: List<Show>? = emptyList()
    )

}