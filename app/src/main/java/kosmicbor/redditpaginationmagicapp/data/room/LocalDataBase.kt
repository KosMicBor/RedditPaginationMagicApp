package kosmicbor.redditpaginationmagicapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [RedditPostLocalDto::class, RedditKeyDto::class],
    exportSchema = false
)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun getLocalRedditPostDao(): MainRedditPostDao
    abstract fun getRedditKeysDao(): MainRedditKeysDao
}