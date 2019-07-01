package id.aasumitro.made.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.aasumitro.made.data.entity.Movie
import id.aasumitro.made.data.entity.Show
import io.reactivex.Maybe

/**
 * Created by A. A. Sumitro on 01/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

@Dao
interface DamovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(movie: Movie)

    @Query("DELETE FROM dmxxi_movie WHERE dmxxi_movie_id IN(:id)")
    fun deleteFavoriteMovie(id: Int)

    @Query("SELECT * FROM dmxxi_movie")
    fun readFavoriteMovie(): Maybe<List<Movie>>

    @Query("SELECT dmxxi_movie_title FROM dmxxi_movie WHERE dmxxi_movie_id IN(:id)")
    fun readMovieFavoriteState(id: Int): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteShow(show: Show)

    @Query("DELETE FROM dmxxi_show WHERE dmxxi_show_id IN(:id)")
    fun deleteFavoriteShow(id: Int)

    @Query("SELECT * FROM dmxxi_show")
    fun readFavoriteShow(): Maybe<List<Show>>

    @Query("SELECT dmxxi_show_ori_name FROM dmxxi_show WHERE dmxxi_show_id IN(:id)")
    fun readShowFavoriteState(id: Int): String

}