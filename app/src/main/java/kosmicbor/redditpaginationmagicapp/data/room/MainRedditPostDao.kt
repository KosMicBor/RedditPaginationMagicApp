package kosmicbor.redditpaginationmagicapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MainRedditPostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<RedditPostLocalDto>)

    @Query("DELETE FROM reddit_posts")
    suspend fun clearAll()
}