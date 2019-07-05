package id.aasumitro.made.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.aasumitro.made.data.entity.Movie
import id.aasumitro.made.data.entity.Show
import id.aasumitro.made.data.source.local.DbConst.DATABASE_NAME

/**
 * Created by A. A. Sumitro on 01/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

@Database(
    entities =
    [
        (Movie::class),
        (Show::class)
    ],
    version = 1
)
abstract class DamovieDb : RoomDatabase() {
    abstract fun damovieDao(): DamovieDao

    companion object {
        @Volatile
        private var INSTANCE: DamovieDb? = null

        fun getInstance(context: Context): DamovieDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DamovieDb::class.java,
                DATABASE_NAME
            ).allowMainThreadQueries().build()
    }
}