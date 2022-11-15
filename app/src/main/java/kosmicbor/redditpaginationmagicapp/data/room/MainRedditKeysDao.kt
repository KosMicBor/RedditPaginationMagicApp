package kosmicbor.redditpaginationmagicapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MainRedditKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RedditKeyDto)

    @Query("DELETE FROM reddit_keys")
    suspend fun clearAll()
}